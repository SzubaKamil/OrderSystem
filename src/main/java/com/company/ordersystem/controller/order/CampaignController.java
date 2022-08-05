package com.company.ordersystem.controller.order;

import com.company.ordersystem.controller.ICrudController;
import com.company.ordersystem.entity.order.Campaign;
import com.company.ordersystem.entity.order.Order;
import com.company.ordersystem.entity.product.Product;
import com.company.ordersystem.service.company.IAddressService;
import com.company.ordersystem.service.company.ICompanyService;
import com.company.ordersystem.service.order.ICampaignService;
import com.company.ordersystem.service.order.IOrderService;
import com.company.ordersystem.service.order.IPaymentTermService;
import com.company.ordersystem.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@RequestMapping (value = "/campaign")
@Controller
public class CampaignController implements ICrudController<Campaign> {

    @Autowired
    ICampaignService campaignService;
    @Autowired
    IAddressService addressService;
    @Autowired
    ICompanyService companyService;
    @Autowired
    IProductService productService;
    @Autowired
    IPaymentTermService paymentTermService;
    @Autowired
    IOrderService orderService;

    private List<Product> productList;

    public CampaignController(List<Product> productList) {
        this.productList = productList;
    }

    @Override
    public String newForm(Model model) {
        Campaign campaign = new Campaign();

        productList = new ArrayList<>();
        campaign.setProductList(productList);

        model.addAttribute("campaign", campaign);
        model.addAttribute("action", "/campaign/save/");
        model.addAttribute("actionDescription", "NOWA KAMPANIA");

        modelForm(model);

        return "/order/campaign-form";
    }

    @Override
    public String listForm(Model model) {
        List<Campaign> campaigns = campaignService.getAll();

        model.addAttribute("campaigns", campaigns);
        model.addAttribute("actionDescription", "LISTA KAMPANI");
        model.addAttribute("searchCampaign", new Campaign());

        return "/order/campaign-list";
    }

    @Override
    public String updateForm(int id, Model model) {
        Campaign campaign;

        if (id > 0){
            campaign =  campaignService.get(id);
            productList = campaign.getProductList();
        } else {
            campaign = new Campaign();
            campaign.setProductList(productList);
        }

        model.addAttribute("campaign" , campaign);
        model.addAttribute("action", "/campaign/update/");
        model.addAttribute("actionDescription", "EDYCJA KAMPANII: " + campaign.getName());

        modelForm(model);

        return "/order/campaign-form";
    }

    @Override
    public String delete(int id, Model model) {
        Campaign campaign = campaignService.get(id);

        boolean result = campaignService.delete(campaign);
        String description = "Usunięcie kampani: " + campaign.getName() + " ";

        model.addAttribute("description", description);
        model.addAttribute("result", result);

        return "result";
    }

    @Override
    public String update(Campaign campaign, Model model) {
        campaign.setProductList(productList);

        String description = "Edycja kampani " + campaign.getName() + " ";

        return saveOrUpdate(campaign, description, model);
    }

    @Override
    public String save(Campaign campaign, Model model) {
        String description = "Dodanie nowego kampani " + campaign.getName() + " ";

        return saveOrUpdate(campaign, description, model);
    }

    @Override
    public String saveOrUpdate(Campaign campaign, String description, Model model) {
        boolean result = campaignService.saveOrUpdate(campaign);

        model.addAttribute("result", result);
        model.addAttribute("description", description);

        return "result";
    }

    @Override
    public void modelForm(Model model) {
        model.addAttribute("deliveryAddresses", addressService.getDeliveryAddresses());
        model.addAttribute("products", productService.getAll());
        model.addAttribute("companies", companyService.getAll());
        model.addAttribute("paymentTerms", paymentTermService.getAll());
        model.addAttribute("product", new Product());
    }

    @RequestMapping("/save-product/{campaignId}")
    public String saveProduct (@ModelAttribute("product") Product product, @PathVariable int campaignId){
        Campaign campaign = campaignService.get(campaignId);
        product = productService.get(product.getId());

        campaign.addProduct(product);
        campaignService.saveOrUpdate(campaign);

        return "redirect:/campaign/update/{campaignId}";
    }

    @RequestMapping("/create-order/{campaignId}/{productId}")
    public String createOrder (@PathVariable int campaignId, @PathVariable int productId){
        Campaign campaign = campaignService.get(campaignId);
        Product product = productService.get(productId);

        Order order = new Order(campaign, product);
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        order.setUsername(username);
        orderService.saveOrUpdate(order);

        campaign.addOrder(order);
        campaignService.saveOrUpdate(campaign);

        return "redirect:/order/update/" + order.getId();
    }

    @RequestMapping("/delete-product/{campaignId}/{productId}")
    public String deleteProduct (@PathVariable int campaignId, @PathVariable int productId){
        Campaign campaign = campaignService.get(campaignId);
        Product product = productService.get(productId);

        campaign.removeProduct(product);
        campaignService.saveOrUpdate(campaign);

        return "redirect:/campaign/update/{campaignId}";
    }

    @RequestMapping(value = "search")
    public String search(Model model, @ModelAttribute("searchCampaign") Campaign searchCampaign){
        List<Campaign> campaigns = campaignService.search(searchCampaign);

        model.addAttribute("campaigns", campaigns);
        model.addAttribute("actionDescription", "LISTA KAMPANI");
        model.addAttribute("searchCampaign", searchCampaign);

        return "/order/campaign-list";
    }
}
