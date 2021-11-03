<template>
  <swiper
      :slides-per-view="'auto'"
      :space-between="30"
      :navigation="true"
      :pagination='{"clickable": true}'
      :centeredSlides="true"
      :loop="true"
      :autoplay='{"delay":4000, "disableOnInteraction":false}'
      @swiper="onSwiper"
      @slideChange="onSlideChange"
  >
    <swiper-slide class="home-banner"><div class="banner-img-area" style='background-image: url("https://img.wavve.com/banner/pooq/2021/20211028_banner_193607.jpg")'><a><img src="https://img.wavve.com/banner/pooq/2021/imgbuild_20211028_193819560.png"/></a></div></swiper-slide>
    <swiper-slide class="home-banner"><div class="banner-img-area" style='background-image: url("https://img.wavve.com/banner/pooq/2021/20211028_banner_234925.jpg")'><a><img src="https://img.wavve.com/banner/pooq/2021/imgbuild_20211028_235024587.png"/></a></div></swiper-slide>
    <swiper-slide class="home-banner"><div class="banner-img-area" style='background-image: url("https://img.wavve.com/banner/pooq/2021/20211013_banner_133100.jpg")'><a><img src="https://img.wavve.com/banner/pooq/2021/imgbuild_20211028_193819560.png"/></a></div></swiper-slide>
  </swiper>
</template>
<script>
import SwiperCore,{Navigation, Pagination, Autoplay} from "swiper";

// Import Swiper Vue.js components
import {Swiper, SwiperSlide} from "swiper/vue";

// Import Swiper styles
import "swiper/swiper-bundle.min.css"
import "swiper/components/navigation/navigation.min.css"
import "swiper/components/pagination/pagination.min.css"


SwiperCore.use([Pagination,Navigation, Autoplay])

export default {
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