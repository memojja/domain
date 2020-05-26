package domain.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Category {
    private String title;
    private Category parent;

    public Category(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==null){
            return false;
        }
        if(obj == this){
            return true;
        }
        if(!(obj instanceof Category)){
            return false;
        }
        Category category = (Category)obj;
        return this.title.equals(category.title);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(title);
    }

    public boolean isParentCategory(Category category) {
        if(parent==null){
            return false;
        } else if(parent.equals(category)){
            return true;
        } else {
            return parent.isParentCategory(category);
        }
    }

}
