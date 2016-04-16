package ru.stqa.pft.soap;

import net.webservicex.GeoIP;
import net.webservicex.GeoIPService;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Created by oleg on 16.04.16.
 */
public class GeoIpServiseTests {


    @Test
    public void testMyIp(){
        GeoIP geoIp = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("194.28.29.152");
        assertEquals(geoIp.getCountryCode(),"RUS");
    }

    @Test
    public void testMyInvalidIp(){
        GeoIP geoIp = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("194.28.29.xxx");
        assertEquals(geoIp.getCountryCode(),null);
    }
}
