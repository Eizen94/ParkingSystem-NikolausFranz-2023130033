/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parkingsystem;

/**
 *
 * @author Nikolaus Franz
 */
public class PlateInfo {
    private final String code;
    private final String region;
    private final String areas;
    
    public PlateInfo(String code, String region, String areas) {
        this.code = code;
        this.region = region;
        this.areas = areas;
    }
    
    public String getCode() { 
        return code; 
    }
    
    public String getRegion() { 
        return region; 
    }
    
    public String getAreas() { 
        return areas; 
    }
}