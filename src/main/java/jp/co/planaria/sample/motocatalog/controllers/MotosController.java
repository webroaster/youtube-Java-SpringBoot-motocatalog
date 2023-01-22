package jp.co.planaria.sample.motocatalog.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.engine.AttributeName;

import jp.co.planaria.sample.bean.Brand;
import jp.co.planaria.sample.bean.Motorcycle;

@Controller
public class MotosController {

  @RequestMapping("/hello")
  public String hello(@RequestParam(name = "name") String name, Model model) {
    model.addAttribute("name", name);
    return "test";
  }

  @GetMapping("/motos")
  public String motos(Model model) {
    // ブランド
    List<Brand> brands = new ArrayList<>();
    brands.add(new Brand("01", "HONDA"));
    brands.add(new Brand("02", "Kawasaki"));
    brands.add(new Brand("03", "YAMAHA"));
    brands.add(new Brand("04", "Suzuki"));

    // // バイク
    List<Motorcycle> motos = new ArrayList<>();
    motos.add(new Motorcycle(1, "GB350", 800, 1, "空冷", 500000, "いい音", new Brand("01", "HONDA"), 1, null, null));
    motos.add(new Motorcycle(2, "Ninja", 800, 2, "水冷", 1000000, "高速走行", new Brand("02", "Kawasaki"), 1, null, null));
    motos.add(new Motorcycle(3, "A900RS", 820, 4, "水冷", 1800000, "かっこいい", new Brand("02", "Kawasaki"), 1, null, null));

    model.addAttribute("brands", brands);
    model.addAttribute("motos", motos);

    return "moto_list";
  }
}
