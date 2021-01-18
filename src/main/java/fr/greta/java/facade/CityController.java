package fr.greta.java.facade;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/city")

public class CityController {

    List<MeteoDTO> meteoDTOList = new ArrayList<>();
    List<CityDTO> cityDTOList = new ArrayList<>();


    public CityController () {
        CityDTO city1 = new CityDTO();
        city1.setId(1);
        city1.setName("Aix-en-Provence");
        cityDTOList.add(city1);

        CityDTO city2 = new CityDTO();
        city2.setId(2);
        city2.setName("Marseille");
        cityDTOList.add(city2);

        CityDTO city3 = new CityDTO();
        city3.setId(3);
        city3.setName("Toulon");
        cityDTOList.add(city3);

        CityDTO city4 = new CityDTO();
        city4.setId(4);
        city4.setName("Montpellier");
        cityDTOList.add(city4);
        //-----------------------------------
        MeteoDTO meteo1 = new MeteoDTO();
        meteo1.setId(11);
        meteo1.setDate("13-01-2021");
        meteo1.setTempMatin(5);
        meteo1.setTempAprem(7);
        meteo1.setCityDTO(city1);
        meteoDTOList.add(meteo1);

        MeteoDTO meteo2 = new MeteoDTO();
        meteo2.setId(22);
        meteo2.setDate("14-01-2021");
        meteo2.setTempMatin(2);
        meteo2.setTempAprem(5);
        meteo2.setCityDTO(city1);
        meteoDTOList.add(meteo2);

        MeteoDTO meteo3 = new MeteoDTO();
        meteo3.setId(33);
        meteo3.setDate("13-01-2021");
        meteo3.setTempMatin(3);
        meteo3.setTempAprem(8);
        meteo3.setCityDTO(city2);
        meteoDTOList.add(meteo3);

        MeteoDTO meteo4 = new MeteoDTO();
        meteo4.setId(44);
        meteo4.setDate("14-01-2021");
        meteo4.setTempMatin(4);
        meteo4.setTempAprem(10);
        meteo4.setCityDTO(city2);
        meteoDTOList.add(meteo4);

        MeteoDTO meteo5 = new MeteoDTO();
        meteo5.setId(55);
        meteo5.setDate("13-01-2021");
        meteo5.setTempMatin(6);
        meteo5.setTempAprem(13);
        meteo5.setCityDTO(city3);
        meteoDTOList.add(meteo5);

        MeteoDTO meteo6 = new MeteoDTO();
        meteo6.setId(66);
        meteo6.setDate("14-01-2021");
        meteo6.setTempMatin(8);
        meteo6.setTempAprem(15);
        meteo6.setCityDTO(city3);
        meteoDTOList.add(meteo6);

        MeteoDTO meteo7 = new MeteoDTO();
        meteo7.setId(77);
        meteo7.setDate("13-01-2021");
        meteo7.setTempMatin(9);
        meteo7.setTempAprem(16);
        meteo7.setCityDTO(city4);
        meteoDTOList.add(meteo7);

        MeteoDTO meteo8 = new MeteoDTO();
        meteo8.setId(88);
        meteo8.setDate("14-01-2021");
        meteo8.setTempMatin(9);
        meteo8.setTempAprem(16);
        meteo8.setCityDTO(city4);
        meteoDTOList.add(meteo8);
    }
    //-----------------------------------------
    
    @GetMapping(path = "/")
    public List<CityDTO> list() {
        return cityDTOList;
    }

    @PostMapping(name = "/")
    public CityDTO save(@RequestBody CityDTO input) {
        if (cityDTOList.contains(input)) {
            return merge(input);
        }
        return create(input);
    }

    @DeleteMapping(path = "/{cityId}")
    public ResponseEntity<Void> remove(@PathVariable(value = "cityId") int cityId) {
        return findById(cityId)
                .map(city -> {
                    cityDTOList.remove(city);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(path = "/meteo/{cityName}/{date}")
    public ResponseEntity<MeteoDTO> findByDateAndName(@PathVariable(value = "cityName") String cityName, @PathVariable(value = "date") String date) {
        return findByDateName(cityName, date)
                .map(meteo -> ResponseEntity.ok(meteo))
                .orElse(ResponseEntity.notFound().build());
    }

    private Optional<MeteoDTO> findByDateName(String cityName, String date) {
        for (MeteoDTO each : meteoDTOList) {
            if (each.getCityDTO().getName().equals(cityName) && each.getDate().equals(date)) {
                return Optional.of(each);
            }
        }
        return Optional.empty();
    }

    private Optional<CityDTO> findById(int cityId) {
        for (CityDTO each : cityDTOList) {
            if (each.getId() == cityId) {
                return Optional.of(each);
            }
        }
        return Optional.empty();
    }

    private CityDTO merge(CityDTO input) {
        cityDTOList.set(cityDTOList.indexOf(input), input);
        return input;
    }

    private CityDTO create(CityDTO input) {
        input.setId(cityDTOList.size() + 1);
        cityDTOList.add(input);
        return input;
    }

}