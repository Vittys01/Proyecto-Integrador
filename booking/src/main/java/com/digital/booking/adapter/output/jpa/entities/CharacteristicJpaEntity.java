package com.digital.booking.adapter.output.jpa.entities;

import com.digital.booking.core.domain.Characteristic;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SQLDelete(sql = "UPDATE characteristics SET soft_delete = true WHERE id = ?")
@Where(clause = "soft_delete=false")
@Table(name = "characteristics")
public class CharacteristicJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "icon")
    private String icon;

    @CreationTimestamp
    @Column(name = "create_timestamp", updatable = false)
    private Timestamp createTimestamp;

    @Column(name = "soft_delete")
    private Boolean softDelete = Boolean.FALSE;

    public static CharacteristicJpaEntity fromDomain(Characteristic characteristic) {
        CharacteristicJpaEntityBuilder builder = CharacteristicJpaEntity.builder()
                .title(characteristic.getTitle())
                .icon(characteristic.getIcon())
                .createTimestamp(characteristic.getCreateTimestamp())
                .softDelete(characteristic.getSoftDelete());
        if(characteristic.getId() != null) builder.id(characteristic.getId());
        return builder.build();
    }

    public static Characteristic toDomain(CharacteristicJpaEntity characteristicJpaEntity) {
        return Characteristic.builder()
                .id(characteristicJpaEntity.getId())
                .title(characteristicJpaEntity.getTitle())
                .icon(characteristicJpaEntity.getIcon())
                .createTimestamp(characteristicJpaEntity.getCreateTimestamp())
                .softDelete(characteristicJpaEntity.getSoftDelete())
                .build();
    }
}
