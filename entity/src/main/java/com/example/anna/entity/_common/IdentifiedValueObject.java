package com.example.anna.entity._common;

import lombok.*;

import javax.persistence.*;

/**
 * 밸류타입 PK
 */
@MappedSuperclass
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public abstract class IdentifiedValueObject extends AbstractBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter(AccessLevel.PACKAGE)
    @Setter(AccessLevel.PACKAGE)
    @Column(name = "ID")
    private Long id;
}
