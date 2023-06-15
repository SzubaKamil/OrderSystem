package com.company.ordersystem.controller.order;


import com.company.ordersystem.MediaTypeUtils;
import com.company.ordersystem.controller.ICrudController;
import com.company.ordersystem.entity.company.Contact;
import com.company.ordersystem.entity.order.Order;
import com.company.ordersystem.entity.user.User;
import com.company.ordersystem.mail.OutlookEmail;
import com.company.ordersystem.printer.PdfPrinter;
import com.company.ordersystem.service.company.ICompanyService;
import com.company.ordersystem.service.company.IContractorService;
import com.company.ordersystem.service.order.IOrderService;
import com.company.ordersystem.service.company.IAddressService;
import com.company.ordersystem.service.order.IPaymentTermService;
import com.company.ordersystem.service.product.IProductService;
import com.company.ordersystem.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.ServletContext;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/order")
public class OrderController implements ICrudController<Order> {

    @Autowired
    IOrderService orderService;
    @Autowired
    IProductService productService;
    @Autowired
    IAddressService addressService;
    @Autowired
    IPaymentTermService paymentTermService;
    @Autowired
    IContractorService contractorService;
    @Autowired
    ICompanyService companyService;
    @Autowired
    IUserService userService;
    @Autowired
    private ServletContext servletContext;

    @Override
    public String newForm(Model model) {
        Order order = new Order();

        model.addAttribute("order", order);
        model.addAttribute("action", "/order/save/");
        model.addAttribute("actionDescription", "NOWE ZLECENIE");
        modelForm(model);

        return "/order/order-form";
    }

    @Override
    public String listForm(Model model) {
        List<Order> orders = orderService.getAll();

        model.addAttribute("orders", orders);
        model.addAttribute("actionDescription", "LISTA ZLECEŃ");
        model.addAttribute("searchOrder", new Order());

        return "/order/order-list";
    }

    @Override
    public String updateForm(int id, Model model) {
        Order order = orderService.get(id);

        model.addAttribute("update", true);
        model.addAttribute("order" , order);
        model.addAttribute("action", "/order/update/");
        model.addAttribute("actionDescription", "EDYCJA ZLECENIA : " + order.getProduct().getName());
        modelForm(model);

        return "/order/order-form";
    }

    @Override
    public String delete(int id, Model model) {
        Order order = orderService.get(id);

        boolean result = orderService.delete(order);
        String description = "Usunięcie zlecenia: " + order.getProduct().getName() + " ";

        model.addAttribute("description", description);
        model.addAttribute("result", result);

        return "result";
    }

    @Override
    public String update(Order order, Model model) {
        String description = "Edycja zlecenia na produkt" + order.getProduct().getName() + " ";
        order.setUsername(getCurrentUser().getUsername());

        return saveOrUpdate(order, description, model);
    }

    @Override
    public String save(Order order, Model model) {
        String description = "Dodanie nowego zlecenia  na produkt" + order.getProduct().getName() + " ";
        order.setUsername(getCurrentUser().getUsername());

        return saveOrUpdate(order, description, model);
    }

    @Override
    public String saveOrUpdate(Order order, String description, Model model) {
        if (order.getCampaign().getId() == 0){
            order.setCampaign(null);
        }

        boolean result = orderService.saveOrUpdate(order);

        model.addAttribute("result", result);
        model.addAttribute("description", description);

        return "result";
    }

    @Override
    public void modelForm(Model model) {
        model.addAttribute("paymentTerms",  paymentTermService.getAll());
        model.addAttribute("deliveryAddresses", addressService.getDeliveryAddresses());
        model.addAttribute("products", productService.getAll());
        model.addAttribute("contractors", contractorService.getAll());
        model.addAttribute("companies", companyService.getAll());
    }

    @RequestMapping(value = "search")
    public String search(Model model, @ModelAttribute("searchOrder") Order searchOrder){
        List<Order> orders;

        if (searchOrder.getProduct().getName().equals("")){
            orders = orderService.getAll();
        }
        else {
            orders = orderService.search(searchOrder);
        }

        /**INSTED FILTER IN METHOD ADD search method  parameter and get from db */

        // FILTER OPTION MOVE TO NEW METHOD
        if (searchOrder.getUpdate() != null){
            // sent
            if (searchOrder.getUpdate() >= 0){
                orders = orders.stream().filter(order -> order.getUpdate() !=null).collect(Collectors.toList());
            }
            // not sent
            if (searchOrder.getUpdate() == -2){
                orders = orders.stream().filter(order ->  order.getUpdate() ==null).collect(Collectors.toList());
            }
        }

        model.addAttribute("orders", orders);
        model.addAttribute("actionDescription", "LISTA ZLECEŃ");
        model.addAttribute("searchOrder", searchOrder);

        return "/order/order-list";
    }

    @RequestMapping(value = "product-history/{productId}/{productName}")
    public String historyProduct (@PathVariable int productId, @PathVariable String productName, Model model){
        List<Order> orders = orderService.getByProductId (productId);

        model.addAttribute("orders", orders);
        model.addAttribute("actionDescription", "HISTORIA PRODUKTU: " + productName);

        return "/order/order-product-history";
    }

    @RequestMapping(value = "contractor-history/{contractorId}/{contractorName}")
    public String historyContractor (@PathVariable int contractorId, @PathVariable String contractorName,  Model model){
        List<Order> orders = orderService.getByContractorID(contractorId);

        model.addAttribute("orders", orders);
        model.addAttribute("actionDescription", "HISTORIA KONTRACHENTA: " + contractorName);

        return "/order/order-contractor-history";
    }

    @RequestMapping(value = "/print-order/{orderId}")
    public ResponseEntity<ByteArrayResource> printOrder(@PathVariable int orderId) throws IOException {
        Order order = orderService.get(orderId);

        return saveFileLocal(savePdfOrder(order));
    }

    @RequestMapping(value = "/print-inquiry/{orderId}")
    public ResponseEntity<ByteArrayResource> printInquiry(@PathVariable int orderId, Model model) throws IOException {
        Order order = orderService.get(orderId);

        return saveFileLocal(savePdfInquiry(order));
    }

    @RequestMapping(value = "/send-order-by-email/{orderId}")
    public ResponseEntity<ByteArrayResource> sendOrderByEmail(@PathVariable int orderId) throws IOException{
        Order order = orderService.get(orderId);
        String fileName = savePdfOrder(order);

        ResponseEntity<ByteArrayResource> responseEntity = saveFileLocal(fileName);

        List<Contact> contactList = new ArrayList<>(order.getContractor().getAddress().getContacts());
        OutlookEmail.newOrderEmail(fileName, contactList, getCurrentUser().getOutlookSetting(), order.getCompany().getCcEmails());

        return responseEntity;
    }

    @RequestMapping(value = "/send-inquiry-by-email/{orderId}")
    public ResponseEntity<ByteArrayResource> sendInquiryByEmail(@PathVariable int orderId) throws IOException{
        Order order = orderService.get(orderId);
        String fileName = savePdfInquiry(order);

        ResponseEntity<ByteArrayResource> responseEntity = saveFileLocal(fileName);

        List<Contact> contactList = new ArrayList<>(order.getContractor().getAddress().getContacts());
        OutlookEmail.newInquiryEmail(fileName, contactList, getCurrentUser().getOutlookSetting());

        return responseEntity;
    }

    private User getCurrentUser (){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.get(username);
    }

    private ResponseEntity<ByteArrayResource> saveFileLocal(String fileName) throws IOException {
        MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(this.servletContext, fileName);
        String directory = Path.of("").toAbsolutePath().toString();

        Path path = Paths.get(directory + "/" + fileName);
        byte[] data = Files.readAllBytes(path);
        ByteArrayResource resource = new ByteArrayResource(data);

        Files.delete(path);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + path.getFileName().toString())
                .contentType(mediaType)
                .contentLength(data.length)
                .body(resource);
    }

    private String savePdfOrder (Order order){
        String fileName;

        boolean newOrder = order.setSent(LocalDate.now());
        orderService.saveOrUpdate(order);

        PdfPrinter pdfPrinter = new PdfPrinter(order, getCurrentUser());

        if (newOrder){
            fileName = pdfPrinter.printOrder();
        }
        else {
            fileName = pdfPrinter.updateOrder();
        }

        return fileName;
    }

    private String savePdfInquiry(Order order) {
        String fileName;

        PdfPrinter pdfPrinter = new PdfPrinter(order, getCurrentUser());
        fileName = pdfPrinter.printInquiry();

        return fileName;
    }
}