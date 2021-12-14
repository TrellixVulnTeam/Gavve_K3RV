<template>
  <div class="container">
    <div class="main" ref="main" :class="{navToggle: navToggle, change: navToggle}">
      <div class="left-nav">
        <div class="nav-title">
          <h2>감귤</h2>
        </div>
        <div>
          <ul>
            <li @click="currentTab = 0;NavToggleBtn();">대시보드</li>
            <li @click="currentTab = 1;NavToggleBtn();">배너설정</li>
            <li @click="currentTab = 2;NavToggleBtn();">몰</li>
            <li @click="currentTab = 3;NavToggleBtn();">루</li>
          </ul>
        </div>
      </div>
      <div class="right-body">
        <div class="nav-title">
          <div class="ham-container" @click="NavToggleBtn()">
            <div class="bar1"></div>
            <div class="bar2"></div>
            <div class="bar3"></div>
          </div>
          <input class="nav-title-input" placeholder="검색" type="text" />
        </div>
        <div class="body-container">
			<div class="tab-contents">
        <div v-show="currentTab == 0">tab1</div>
        <div v-show="currentTab == 1">
          <h1>배너 추가/삭제</h1>
          <div class="banner-command">
            <BannerAddBox v-for="(item, index) in this.$store.state.bannerData" :key="item.id" :bannerindex="index" :bannerBoxData="item"></BannerAddBox>
          </div>
          <div class="banner-add-box"><div style="height: 50px"><div @click="bannerAdd" class="banner-modify-btn">추가</div></div></div>
        </div>
        <div v-show="currentTab == 2">tab3</div>
        <div v-show="currentTab == 3">tab4</div>
      </div>
		</div>
      </div>
    </div>
  </div>
</template>
<script>
import { Options, Vue } from "vue-class-component";
import Axios from "axios";

import BannerAddBox from "./BannerAddBox.vue"

@Options({
  components: {BannerAddBox},
})
export default class Admin extends Vue {
  navToggle = false;
  currentTab = 1;
  date = null;

  NavToggleBtn() {
    this.navToggle = !this.navToggle;
  }

  created(){
    this.$store.commit("ADD_BANNER");
  }

  bannerAdd(){
    this.$store.commit("ADD_BANNER");
  }
  
}
</script>

<style
  src="../../../../../src/main/resources/css/Admin.styl"
  lang="stylus"
></style>
