package com.oyameen.SpringBootWebServiceBasics.model;

import lombok.*;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@EqualsAndHashCode
public class EmployeeRequestDto {

    private Long id;
    private String name;
    private String jobTitle;
    private Long vehicleId;
    private List<Long> laptopIds;
    private List<Long> mobileIds;
    private List<Long> projectIds;
}
