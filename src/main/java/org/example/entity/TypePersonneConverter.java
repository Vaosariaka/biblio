
package org.example.entity;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class TypePersonneConverter implements AttributeConverter<Personne.TypePersonne, String> {

    @Override
    public String convertToDatabaseColumn(Personne.TypePersonne attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.name();
    }

    @Override
    public Personne.TypePersonne convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        return Personne.TypePersonne.valueOf(dbData);
    }
}
