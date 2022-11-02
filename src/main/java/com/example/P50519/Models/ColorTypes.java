package com.example.P50519.Models;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class ColorTypes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idcolorType;

    private String colorType;

    @OneToMany(mappedBy = "colorType", fetch = FetchType.EAGER)
    @NotFound(action = NotFoundAction.IGNORE)
    private Collection<Color> colors;

    public ColorTypes() {
    }

    public ColorTypes(String colorType, Collection<Color> colors) {
        this.colorType = colorType;
        this.colors = colors;
    }

    public Long getIdcolorType() {
        return idcolorType;
    }

    public void setIdcolorType(Long idcolorType) {
        this.idcolorType = idcolorType;
    }

    public String getColorType() {
        return colorType;
    }

    public void setColorType(String colorType) {
        this.colorType = colorType;
    }

    public Collection<Color> getColors() {
        return colors;
    }

    public void setColors(Collection<Color> colors) {
        this.colors = colors;
    }
}
