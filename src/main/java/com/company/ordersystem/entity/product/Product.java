package com.company.ordersystem.entity.product;

import com.company.ordersystem.hibernate.ReadOnlyCollectionPersister;
import com.company.ordersystem.entity.order.Campaign;
import org.hibernate.annotations.Persister;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product")
public class Product implements Cloneable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(cascade= CascadeType.ALL)
    @JoinColumn (name = "product_id")
    private List<Code> codeList;

    @ManyToOne
    @JoinColumn(name = "format_id")
    private Format format;

    @Column(name = "additional")
    private String additional;

    @ManyToOne
    @JoinColumn(name = "paper_id")
    private Paper paper;

    @ManyToOne
    @JoinColumn (name = "color_id" )
    private Color color;

    @Column(name = "number_pages")
    private String numberPages;

    @Column (name = "perforation")
    private String perforation;

    @Column (name = "covering")
    private String covering;

    @Column (name = "flap")
    private String flap;

    @Column (name = "[window]")
    private String window;

    @Column (name = "glued")
    private String glued;

    @Column (name = "printing_finishing")
    private String printingFinishing;

    @ManyToOne
    @JoinColumn (name = "paper_inside_id")
    private Paper paperInside;

    @ManyToOne
    @JoinColumn (name = "color_inside_id")
    private Color colorInside;

    @Column(name = "cover")
    private String cover;

    @ManyToOne
    @JoinColumn (name = "paper_cover_id")
    private Paper paperCover;

    @ManyToOne
    @JoinColumn (name = "color_cover_id")
    private Color colorCover;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "campaign_products",
            joinColumns =@JoinColumn(name = "product_id", updatable = false, insertable = false),
            inverseJoinColumns = @JoinColumn(name = "campaign_id", updatable = false, insertable = false))
    @Persister(impl = ReadOnlyCollectionPersister.class)
    List<Campaign> campaignList;

    @OneToOne
    @JoinColumn(name = "product_eng_id")
    private Product productEng;


    public Product() {
        this.codeList = new ArrayList<>();
    }

    public Product(Product product){
        this.name = product.getName();
        this.codeList = new ArrayList<>();
        this.format = product.getFormat();
        this.additional = product.getAdditional();
        this.paper = product.getPaper();
        this.color = product.getColor();
        this.numberPages = product.getNumberPages();
        this.perforation = product.getPerforation();
        this.covering = product.getCovering();
        this.flap = product.getFlap();
        this.window = product.getWindow();
        this.glued = product.getGlued();
        this.printingFinishing = product.getPrintingFinishing();
        this.paperInside = product.getPaperInside();
        this.colorInside = product.getColorInside();
        this.cover = product.getCover();
        this.paperCover = product.getPaperCover();
        this.colorCover = product.getColorCover();
        this.productEng = product.getProductEng();

        for(Code code: product.getCodeList()){
            try {
                this.codeList.add(code.clone());
            }
            catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Code> getCodeList() {
        return codeList;
    }

    public void setCodeList(List<Code> codeList) {
        this.codeList = codeList;
    }

    public Format getFormat() {
        return format;
    }

    public void setFormat(Format format) {
        this.format = format;
    }

    public String getAdditional() {
        return additional;
    }

    public void setAdditional(String additional) {
        this.additional = additional;
    }

    public Paper getPaper() {
        return paper;
    }

    public void setPaper(Paper paper) {
        this.paper = paper;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getNumberPages() {
        return numberPages;
    }

    public void setNumberPages(String numberPages) {
        this.numberPages = numberPages;
    }

    public String getPerforation() {
        return perforation;
    }

    public void setPerforation(String perforation) {
        this.perforation = perforation;
    }

    public String getCovering() {
        return covering;
    }

    public void setCovering(String covering) {
        this.covering = covering;
    }

    public String getFlap() {
        return flap;
    }

    public void setFlap(String flap) {
        this.flap = flap;
    }

    public String getWindow() {
        return window;
    }

    public void setWindow(String window) {
        this.window = window;
    }

    public String getGlued() {
        return glued;
    }

    public void setGlued(String glued) {
        this.glued = glued;
    }

    public String getPrintingFinishing() {
        return printingFinishing;
    }

    public void setPrintingFinishing(String printingFinishing) {
        this.printingFinishing = printingFinishing;
    }

    public Paper getPaperInside() {
        return paperInside;
    }

    public void setPaperInside(Paper paperInside) {
        this.paperInside = paperInside;
    }

    public Color getColorInside() {
        return colorInside;
    }

    public void setColorInside(Color colorInside) {
        this.colorInside = colorInside;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Paper getPaperCover() {
        return paperCover;
    }

    public void setPaperCover(Paper paperCover) {
        this.paperCover = paperCover;
    }

    public Color getColorCover() {
        return colorCover;
    }

    public void setColorCover(Color colorCover) {
        this.colorCover = colorCover;
    }

    public List<Campaign> getCampaignList() {
        return campaignList;
    }

    public void setCampaignList(List<Campaign> campaignList) {
        this.campaignList = campaignList;
    }

    public Product getProductEng() {
        return productEng;
    }

    public void setProductEng(Product productEng) {
        this.productEng = productEng;
    }

    public void addCode (Code code){
        this.codeList.add(code);
        code.setProductId(id);
    }

    public void removeCode(Code code) {
        this.codeList.remove(code);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", codeList=" + codeList +
                ", format=" + format +
                ", additional='" + additional + '\'' +
                ", paper=" + paper +
                ", color=" + color +
                ", numberPages='" + numberPages + '\'' +
                ", perforation='" + perforation + '\'' +
                ", covering='" + covering + '\'' +
                ", flap='" + flap + '\'' +
                ", window='" + window + '\'' +
                ", glued='" + glued + '\'' +
                ", printingFinishing='" + printingFinishing + '\'' +
                ", paperInside=" + paperInside +
                ", colorInside=" + colorInside +
                ", cover='" + cover + '\'' +
                ", paperCover=" + paperCover +
                ", colorCover=" + colorCover +
                ", campaigns=" + campaignList +
                '}';
    }

    @Override
    public Product clone() throws CloneNotSupportedException {
        Product productEngTemp = null;
        if (this.productEng != null){
            productEngTemp = this.productEng.clone();
        }

        Product product = new Product(this);
        product.setProductEng(productEngTemp);

        return product;
    }
}
