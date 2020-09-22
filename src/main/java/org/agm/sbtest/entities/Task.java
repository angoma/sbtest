package org.agm.sbtest.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "APPLICATION")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String content;

    @Column
    private Boolean finished;

    @Column
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime creationDate;

    @Column
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime finishedDate;
}
