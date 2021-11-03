<template>
  <div>
    <br><br>
    <div>
      <h1>{{ bannerTitle }}<span style="float:right;font-size: 20px"><a>더 보기</a></span></h1>
    </div>
    <br><br>
    <swiper
        :slides-per-view="3"
        :space-between="30"
        :slidesPerGroup="3"
        :loopFillGroupWithBlank="true"
        @swiper="onSwiper"
        @slideChange="onSlideChange"
    >
      <swiper-slide class="recommand-banner" v-for="n in 9">
        <div class="thumb-image"><img src="https://img.wavve.com/202108/20210812/49227be55949cb795d86a156544c1d8f_w720_h1038_q75.jpg"/>
        </div>
        <div class="recommand-banner-title"><span>홍천기</span></div>
      </swiper-slide>

    </swiper>
  </div>
</template>
<script>
import SwiperCore, {Scrollbar} from "swiper";

// Import Swiper Vue.js components
import {Swiper, SwiperSlide} from "swiper/vue";

// Import Swiper styles
import "swiper/swiper-bundle.min.css"
import "swiper/components/navigation/navigation.min.css"
import "swiper/components/pagination/pagination.min.css"

SwiperCore.use([Scrollbar])

export default {
  props: ["bannerTitle"],
  components: {
    Swiper,
    SwiperSlide,
  },
  setup() {
    const onSwiper = (swiper) => {
      console.log(swiper);
    };
    const onSlideChange = () => {
      console.log("slide change");
    };
    return {
      onSwiper,
      onSlideChange
    };
  },
};
</script>