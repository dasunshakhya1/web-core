package models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductData {

    private String name;
    private String price;
    private String description;
    private String cartButton;
    private int id;


    public double getPrice() {
        return Double.parseDouble(price.replace("$", ""));
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductData that = (ProductData) o;
        return name.equals(that.name) && price.equals(that.price) && description.equals(that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
