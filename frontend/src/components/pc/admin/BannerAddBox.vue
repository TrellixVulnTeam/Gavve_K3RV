<template>
  <div class="banner-box">
    <div class="banner-box-delete" @click="deleteBanner(this.bannerinedx)">✕</div>
    <div class="banner-img">이미지.jpg</div>
    <div class="banner-img">글씨.png</div>
    <div class="banner-datepicker">
      <Datepicker locale="kr" placeholder="기간 선택" range v-model="date" format="yyyy/MM/dd HH:mm"></Datepicker>
    </div>
    <div class="banner-linkbox">
      <input type="text" placeholder="링크 입력" />
    </div>
  </div>
</template>
<script>
import { Options, Vue } from "vue-class-component";
import { Prop} from "vue-property-decorator";
import Datepicker from "vue3-date-time-picker";
import "vue3-date-time-picker/dist/main.css";

@Options({
  props:{
    bannerinedx: Number
  },
  components: { Datepicker, Prop },
})



export default class BannerAddBox extends Vue {
  date = null;
  
  deleteBanner(idx) {
    this.$store.commit('REMOVE_BANNER',idx);
  }
}
</script>
