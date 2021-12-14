<template>
  <div class="banner-box">
    <div class="banner-box-delete" @click="deleteBanner(this.bannerindex)">✕</div>
    <div
      class="banner-img-box"
      @dragover.prevent
      @drop.prevent="drop($event,'img')"
      @click="clickUploadImage"
    >
    이미지.jpg
      <img
        :ref="`img${bannerindex}`"
        class="banner-upload-image"
        :src="getImageUrl()"
        onerror="this.style.visibility='hidden'"
      />
      <input
      :ref="`imgupload${bannerindex}`"
        id="bgImage"
        type="file"
        name="bgImage"
        accept="image.*"
        multiple="multiple"
        class="hidden"
        @change="imageUploadChange($event)"
      />
    </div>

    <div
      class="banner-img-box"
      @dragover.prevent
      @drop.prevent="drop($event,'word')"
      @click="clickUploadWord"
    >
      글.png
      <img
        :ref="`word${bannerindex}`"
        class="banner-upload-image"
        :src="this.$store.state.bannerData[this.bannerindex].word"
        onerror="this.style.visibility='hidden'"
      />
      <input
        :ref="`wordupload${bannerindex}`"
        id="fontImage"
        type="file"
        name="wordImage"
        accept="image.*"
        multiple="multiple"
        class="hidden"
        @change="imageUploadChange($event)"
      />
    </div>

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
import axios from "axios";

@Options({
  props:{
    bannerindex: Number,
    bannerBoxData: Object,
  },
  components: { Datepicker, Prop },
})



export default class BannerAddBox extends Vue {
  date = null;


  getImageUrl(){
    if(this.$store.state.bannerData[this.bannerindex].img === "temp"){
      return "";
    }else{
      return this.$store.state.bannerData[this.bannerindex].img;
    }
  }


  deleteBanner(idx) {
    this.$store.commit('REMOVE_BANNER',idx);
  }

  setBannerImg(payload){
    this.$store.commit('SET_BANNER_IMG',payload);
  }

  drop(event, type){    
    if(type==="img"){
      this.inputImageDrop(event.dataTransfer.files);
    }else if(type==="word"){
      this.inputWordDrop(event.dataTransfer.files);
    }

  }

  imageUploadChange(event){
    this.uploadImage(event.target.files[0]);
  }

  uploadImage(file){
    console.log("uploadImage");
    let form = new FormData();
    let idx = this.bannerindex;
    form.append('image',file);
    let payload = {};
    axios.post("/api/banner-image",form,{headers:{'Content-Type':'multipart/form-data'}
    }).then(res => {
      payload ={
        index:idx,
        src:res.data.path
      };
      this.$refs['img'+this.bannerindex].src = payload.src;
      //this.setBannerImg(payload);
    });
  }

  clickUploadImage(){
    this.$refs['imgupload'+this.bannerindex].click();
  }

  clickUploadWord(){
    this.$refs['wordupload'+this.bannerindex].click();
  }

  inputImageDrop(files){
    if(files.length){
      var file = files[0];
      if(!/^image\//.test(file.type)){
        alert("이미지 파일만 asdasd가능합니다.");
        return false;
      }
    this.uploadImage(file);

    }
  }

  inputWordDrop(files){
    if(files.length){
      var file = files[0];
      if(!/^image\//.test(file.type)){
        alert("이미지 파일만 가능합니다.");
        return false;
      }



    }
  }
}
</script>
