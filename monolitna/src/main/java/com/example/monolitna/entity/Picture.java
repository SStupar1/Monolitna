package com.example.monolitna.entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Picture {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String type;

    @Column(name = "pic_byte", length = 1000)
    private byte[] picByte;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Ad ad;

    public Picture(String name, String type, byte[] picByte, Ad ad) {
        this.name = name;
        this.type = type;
        this.picByte = picByte;
        this.ad = ad;
    }

    public Picture(String originalFilename, String contentType, byte[] compressBytes) {
        this.name = originalFilename;
        this.type = contentType;
        this.picByte = compressBytes;
    }
}

