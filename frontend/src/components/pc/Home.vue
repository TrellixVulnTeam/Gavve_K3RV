<template>
  <div class="container">
    <div class="main">
      <div class="top-nav">
        <div class="header">
          <div class="header-nav">
            <ul>
              <li><a href="#">로그인</a></li>
              <li><a href="#">회원가입</a></li>
            </ul>
          </div>
        </div>
        <div>
          <div class="header-gnb">
            <h1 class="logo"><a href="/">감귤</a></h1>
          </div>
        </div>
      </div>
      <div class="main-banners">
        <HomeBanner></HomeBanner>
      </div>
      <div class="g-contents">
        <div class="mobile-content">
          <transition-group name="fade" tag="div" appear>
            <MobileRecomandBanner
              v-for="n in 4"
              :key="n"
              :bannerTitle="recommandBanner"
            ></MobileRecomandBanner>
          </transition-group>
        </div>
        <div class="desktop-content">
          <transition-group name="fade" tag="div" appear>
            <RecommandBanner
              v-for="n in 4"
              :key="n"
              :bannerTitle="recommandBanner"
            ></RecommandBanner>
          </transition-group>
        </div>
      </div>
    </div>
  </div>
</template>
<script lang="ts">
import { Options, Vue } from "vue-class-component";
import HomeBanner from "@/components/HomeBanner.vue";
import RecommandBanner from "@/components/pc/RecommandBanner.vue";
import MobileRecomandBanner from "@/components/mobile/RecommandBanner.vue";
import Axios from "axios";

@Options({
  components: {
    HomeBanner,
    RecommandBanner,
    MobileRecomandBanner,
  },
})
export default class Home extends Vue {
  recommandBanner = "사용자 추천";
  temp = "뭐지";

  getBannerData(): void {
    Axios.get("http://localhost:9999/api/hi")
      .then(function (response) {
        alert(response.data);
      })
      .catch(function (error) {});
  }
}
</script>

<style
  src="../../../../src/main/resources/css/Home.styl"
  lang="stylus"
></style>
