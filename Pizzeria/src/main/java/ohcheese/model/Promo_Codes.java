package ohcheese.model;

import javax.persistence.*;

@Entity
@Table(name = "Promo_Codes")
public class Promo_Codes {
    @Id
    @Column(name = "Promo_Code_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "Promo_Code_Name")
    private String promo_Code;
    @Column(name = "Discount")
    private int percent_Off;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getPromo_Code() { return promo_Code; }
    public void setPromo_Code(String promo_Code) { this.promo_Code = promo_Code; }

    public int getPercent_Off() { return percent_Off; }
    public void setPercent_Off(int percent_Off) { this.percent_Off = percent_Off; }

    public Promo_Codes() { }

    public Promo_Codes(String promo_Code, int percent_Off) {
        this.promo_Code = promo_Code;
        this.percent_Off = percent_Off;
    }
}
