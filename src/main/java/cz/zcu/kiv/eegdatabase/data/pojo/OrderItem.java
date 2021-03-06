package cz.zcu.kiv.eegdatabase.data.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "EEG_ORDER_ITEM")
public class OrderItem implements Serializable, Comparable<OrderItem> {

    private static final long serialVersionUID = -7040903043604899895L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ORDER_ITEM_ID")
    private int id;
    
    @ManyToOne
    @JoinColumn(name = "ORDER_ID", nullable = false)
    private Order order;

    @Column(name = "PRICE", nullable = false, precision = 19, scale = 2)
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "EXPERIMENT")
    private Experiment experiment;
    
    @ManyToOne
    @JoinColumn(name = "EXPERIMENT_PACKAGE")
    private ExperimentPackage experimentPackage;

    @ManyToOne
    @JoinColumn(name = "MEMBERSHIP_PLAN")
    private MembershipPlan membershipPlan;

    @ManyToOne
    @JoinColumn(name = "RESEARCH_GROUP_ID")
    private ResearchGroup researchGroup;
    
    @ManyToOne
    @JoinColumn(name = "LICENSE")
    private License license;

    @ManyToOne
    @JoinColumn(name = "PROMOCODE")
    private PromoCode promoCode;
    
    
    public OrderItem() { }

    public OrderItem(MembershipPlan plan, Order order) {
        this.membershipPlan = plan;
        this.order = order;
        this.price = plan.getPrice() != null ? plan.getPrice() : BigDecimal.ZERO;
    }

    public OrderItem(MembershipPlan plan, ResearchGroup researchGroup, Order order) {
        this.membershipPlan = plan;
        this.order = order;
        this.researchGroup = researchGroup;
        this.price = plan.getPrice() != null ? plan.getPrice() : BigDecimal.ZERO;
    }

    public OrderItem(ExperimentLicence experimentLicense, Order order) {
        this.experiment = experimentLicense.getExperiment();
        this.license = experimentLicense.getLicense();
        this.price = experimentLicense.getPrice();
        this.order = order;
    }

    public OrderItem(ExperimentPackageLicense experimentPackageLicense, Order order) {
        this.experimentPackage = experimentPackageLicense.getExperimentPackage();
        this.license = experimentPackageLicense.getLicense();
        this.price = experimentPackageLicense.getPrice();
        this.order = order;
    }

    public ResearchGroup getResearchGroup() {
        return researchGroup;
    }

    public void setResearchGroup(ResearchGroup researchGroup) {
        this.researchGroup = researchGroup;
    }

    public MembershipPlan getMembershipPlan() {
        return membershipPlan;
    }

    public void setMembershipPlan(MembershipPlan membershipPlan) {
        this.membershipPlan = membershipPlan;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Experiment getExperiment() {
        return experiment;
    }

    public void setExperiment(Experiment experiment) {
        this.experiment = experiment;
    }

    public ExperimentPackage getExperimentPackage() {
        return experimentPackage;
    }

    public void setExperimentPackage(ExperimentPackage experimentPackage) {
        this.experimentPackage = experimentPackage;
    }
    
    public License getLicense() {
        return license;
    }
    
    public void setLicense(License license) {
        this.license = license;
    }

    public PromoCode getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(PromoCode promoCode) {
        this.promoCode = promoCode;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof OrderItem)) {
            return false;
        }
        OrderItem other = (OrderItem) obj;
        if (id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(OrderItem o) {

        if (o == null) {
            return 1;
        }

        return (id < o.getId()) ? -1 : ((id == o.getId()) ? 0 : 1);
    }

}
