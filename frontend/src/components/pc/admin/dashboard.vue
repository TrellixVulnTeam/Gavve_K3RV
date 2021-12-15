<template>
  <div class="container">
    <div class="main" ref="main" :class="{navToggle: navToggle, change: navToggle}">
      <div class="left-nav">
        <div class="nav-title">
          <h2>감귤</h2>
        </div>
        <div>
          <ul>
            <li @click="setCurrentTab(0);NavToggleBtn();">대시보드</li>
            <li @click="setCurrentTab(1);NavToggleBtn();">배너설정</li>
            <li @click="setCurrentTab(2);NavToggleBtn();">몰</li>
            <li @click="setCurrentTab(3);avToggleBtn();">루</li>
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
        <div v-show="currentTab == 1"> <!-- tab2 -->
          
          <div class="banner-command">
            <h1>배너 추가/삭제</h1>
            <BannerAddBox v-for="(item, index) in this.$store.state.bannerData" :key="item.id" :bannerindex="index" :bannerBoxData="item"></BannerAddBox>
          </div>
          <div class="banner-add-box"><div style="height: 50px"><div @click="bannerAdd" class="banner-modify-btn">추가</div><div @click="bannerAdd" class="banner-modify-btn">저장</div></div></div>
        </div>
        <div v-show="currentTab == 2">tab3</div>
        <div v-show="currentTab == 3">tab4</div>
      </div>
		</div>
      </div>
    </div>
  </div>
</template>
<script lang="ts">
import { Options, Vue } from "vue-class-component";
import axios from "axios";

import BannerAddBox from "./BannerAddBox.vue"

@Options({
  components: {BannerAddBox},
})
export default class Admin extends Vue {
  navToggle = false;
  currentTab = 1;
  scrolled = false;
  date = null;
  bannerData = [];

  handleScroll(){
    this.scrolled = window.scrollY > 60;
  }

  created(){
    window.document.addEventListener('scroll',this.handleScroll);
  }

  unmounted(){
    window.document.removeEventListener('scroll',this.handleScroll);
  }
  

  setCurrentTab(val){
    this.currentTab = val;
    let vm = this;
    switch(val){
      case 0:
        console.log("DashboardLoading");
        break;
      case 1:
        axios.get("/api/banner-list").then((res)=>{
          this.$store.state.bannerData = res.data;
          console.log("BannerLoading");
        }).catch((err)=>{
          console.log(err);
        })
        break;
    }
  }

  bannerAdd(){
    this.$store.commit("ADD_BANNER");
  }

  NavToggleBtn() {
    this.navToggle = !this.navToggle;
  }
  
}
</script>

<style
  src="../../../../../src/main/resources/css/Admin.styl"
  lang="stylus"
></style>
