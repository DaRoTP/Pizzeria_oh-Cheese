package ohcheese.model;

import javax.persistence.*;

@Entity
@Table(name = "Size")
public class Size {
    @Id
    @Column(name = "Size_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "Size_Name")
    private String size;
    @Column(name = "Price")
    private String price;

    public Size() { }

    public Size(String size, String price) {
        this.size = size;
        this.price = price;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }

    public String getPrice() { return price; }
    public void setPrice(String price) { this.price = price; }


}
