package br.com.helpdev.jynx.dataprovider.database.entity;

import br.com.helpdev.jynx.core.entity.Status;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class LabelDetectorEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID uuid;

    @ManyToOne
    private ImageEntity imageEntity;

    @Enumerated(EnumType.STRING)
    private Status status;

    public void updateStatus(final Status status) {
        update("status", status);
    }
}
