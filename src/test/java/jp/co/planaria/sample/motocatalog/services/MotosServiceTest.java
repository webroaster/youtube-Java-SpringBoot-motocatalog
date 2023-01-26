package jp.co.planaria.sample.motocatalog.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import jp.co.planaria.sample.motocatalog.beans.Motorcycle;
import jp.co.planaria.sample.motocatalog.beans.SearchForm;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MotosServiceTest {

  // 処理の時間差でassertFailになるため分までを確認
  DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");

  @Autowired
  MotosService service;

  // @Test
  // void バイク情報を全件検索できる() {
  //   SearchCondition condition = new SearchCondition();
  //   List<Motorcycle> motos = service.getMotos(condition);

  //   // 検索結果の件数確認
  //   assertThat(motos.size()).isEqualTo(2);

  //   // 検索結果の項目の確認
  //   Motorcycle moto = motos.get(0);
  //   assertThat(moto).isNotNull();
  //   assertThat(moto.getMotoNo()).isEqualTo(1);
  //   assertThat(moto.getMotoName()).isEqualTo("GB350");
  //   assertThat(moto.getCooling()).isEqualTo("空冷");
  //   assertThat(moto.getBrand().getBrandName()).isEqualTo("HONDA");

  // }

  /*
   * テストケース別
   */

   // バイク一覧取得・条件： ブランドID
    @DisplayName("バイク一覧取得 条件：ブランドID")
    @ParameterizedTest
    @CsvSource({"01, HONDA", "02, Kawasaki", "03, YAMAHA"})
    void test001(String brandId, String brandName) {
      SearchForm condition = new SearchForm();
      condition.setBrandId(brandId); // HONDA

      List<Motorcycle> motos = service.getMotos(condition); // テスト対象
      assertThat(motos.size()).isGreaterThanOrEqualTo(1); // 1以上
      for (Motorcycle moto : motos) {
        assertThat(moto.getBrand().getBrandName()).isEqualTo(brandName);
      }
    }

   // バイク一覧取得・条件： ブランドID 該当なし
    @DisplayName("バイク一覧取得・条件： ブランドID 該当なし")
    @Test
    void test002() {
      SearchForm condition = new SearchForm();
      condition.setBrandId("99"); // HONDA

      List<Motorcycle> motos = service.getMotos(condition); // テスト対象
      assertThat(motos.size()).isEqualTo(0); // 1以上
    }

   // バイク一覧取得・条件： バイク名-完全一致
    @DisplayName("バイク一覧取得・条件： バイク名-完全一致")
    @ParameterizedTest
    @CsvSource({"Z900RS CAFE", "YZF-R1", "Rebel250"})
    void test003(String motoName) {
      SearchForm condition = new SearchForm();
      condition.setKeyword(motoName);

      List<Motorcycle> motos = service.getMotos(condition); // テスト対象
      assertThat(motos.size()).isGreaterThanOrEqualTo(1); // 1以上
      for (Motorcycle moto : motos) {
        assertThat(moto.getMotoName()).isEqualTo(motoName);
      }
    }

   // バイク一覧取得・条件： バイク名-前方一致
   // バイク一覧取得・条件： バイク名-後方一致
   // バイク一覧取得・条件： バイク名-部分一致
    @DisplayName("バイク一覧取得・条件： バイク名-部分一致")
    @ParameterizedTest
    @CsvSource({"Z900RS CA, Z900RS CAFE", "F-R1, YZF-R1", "bel25, Rebel250"})
    void test004(String keyword, String motoName) {
      SearchForm condition = new SearchForm();
      condition.setKeyword(keyword);

      List<Motorcycle> motos = service.getMotos(condition); // テスト対象
      assertThat(motos.size()).isGreaterThanOrEqualTo(1); // 1以上
      for (Motorcycle moto : motos) {
        assertThat(moto.getMotoName()).isEqualTo(motoName);
      }
    }

   // バイク一覧取得・条件： バイク名-該当なし
    @DisplayName("バイク一覧取得・条件： バイク名-該当なし")
    @Test
    void test005() {
      SearchForm condition = new SearchForm();
      condition.setKeyword("Ninja 400");

      List<Motorcycle> motos = service.getMotos(condition); // テスト対象
      assertThat(motos.size()).isEqualTo(0); // 1以上
    }

   // バイク一覧取得・条件： ブランドID - バイク名
    @DisplayName("バイク一覧取得・条件： ブランドID - バイク名")
    @ParameterizedTest
    @CsvSource({"05, III, V7 III Racer 10th ANNIVERSARY", "02, 0 CAFE, W800 CAFE", "03, Final, SR400 Final Edition"})
    void test006(String brandId, String keyword, String motoName) {
      SearchForm condition = new SearchForm();
      condition.setBrandId(brandId);
      condition.setKeyword(keyword);

      List<Motorcycle> motos = service.getMotos(condition); // テスト対象
      assertThat(motos.size()).isGreaterThanOrEqualTo(1); // 1以上
      for (Motorcycle moto : motos) {
        assertThat(moto.getMotoName()).startsWith(motoName);
      }
    }

   // バイク一覧取得・条件： ブランドID - バイク名 該当なし
    @DisplayName("バイク一覧取得・条件： ブランドID - バイク名 該当なし")
    @ParameterizedTest
    @CsvSource({"01, Z900RS", "03, R99"})
    void test007(String brandId, String keyword) {
      SearchForm condition = new SearchForm();
      condition.setBrandId(brandId);
      condition.setKeyword(keyword);

      List<Motorcycle> motos = service.getMotos(condition); // テスト対象
      assertThat(motos.size()).isGreaterThanOrEqualTo(0); // 1以上
    }

   // バイク一覧取得・条件： なし 全件該当
    @DisplayName("バイク一覧取得・条件： なし 全件該当")
    @Test
    void test008() {
      SearchForm condition = new SearchForm();

      List<Motorcycle> motos = service.getMotos(condition); // テスト対象
      assertThat(motos.size()).isEqualTo(9); // 1以上
    }

   // バイク一覧取得・条件： バイク番号
    @DisplayName("バイク一覧取得・条件： バイク番号")
    @ParameterizedTest
    @CsvSource({"1, GB350", "05, Rebel250", "7, SR400 Final Edition"})
    void test009(int motoNo, String motoName) {
      Motorcycle moto = service.getMotos(motoNo); // テスト対象
      assertThat(moto.getMotoName()).isEqualTo(motoName);
    }

   // バイク一覧取得・条件： バイク番号
    @DisplayName("バイク一覧取得・条件： バイク番号")
    @Test
    void test010() {
      Motorcycle moto = service.getMotos(1); // テスト対象
      assertThat(moto.getMotoNo()).isEqualTo(1);
      assertThat(moto.getMotoName()).isEqualTo("GB350");
      assertThat(moto.getSeatHeight()).isEqualTo(800);
      assertThat(moto.getCylinder()).isEqualTo(1);
      assertThat(moto.getCylinder()).isEqualTo(1);
      assertThat(moto.getCylinder()).isEqualTo(1);
      assertThat(moto.getCooling()).isEqualTo("空冷");
      assertThat(moto.getPrice()).isEqualTo(550000);
      assertThat(moto.getComment()).isEqualTo("エンジン音がいい");
      assertThat(moto.getBrand().getBrandId()).isEqualTo("01");
      assertThat(moto.getInsDt().format(dtFormatter)).isEqualTo(LocalDateTime.now().format(dtFormatter));
      assertThat(moto.getUpdDt()).isNull();
    }

   // バイク情報更新
    @DisplayName("バイク情報更新")
    @Test
    @Transactional
    @Rollback
    void test011() {
      Motorcycle before = service.getMotos(1);
      before.setMotoName("motomoto");

      service.save(before); // 更新（保存）

      Motorcycle after = service.getMotos(1); // 変更後のバイク情報
      assertThat(after.getMotoName()).isEqualTo("motomoto");
      assertThat(after.getVersion()).isEqualTo(before.getVersion() + 1);
    }
}
