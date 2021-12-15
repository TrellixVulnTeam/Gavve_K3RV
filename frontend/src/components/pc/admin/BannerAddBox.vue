<template>
  <div class="banner-box">
    <div class="banner-box-delete" @click="deleteBanner(this.bannerindex)">✕</div>
    <div
      class="banner-img-box"
      @dragover.prevent
      @drop.prevent="drop($event,'img')"
      @click="clickUploadImage"
    >
      <div
        class="banner-image-delete"
        @click="deleteUploadImage(this.$store.state.bannerData[this.bannerindex].img,'img')"
        v-show="this.$store.state.bannerData[this.bannerindex].img != ''"
      >✕</div>이미지.jpg
      <img
        :ref="`img${bannerindex}`"
        class="banner-upload-image"
        :src="this.$store.state.bannerData[this.bannerindex].img"
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
      <div
        class="banner-image-delete"
        @click="deleteUploadImage(this.$store.state.bannerData[this.bannerindex].word,'word')"
        v-show="this.$store.state.bannerData[this.bannerindex].word != ''"
      >✕</div>글.png
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
        @change="wordUploadChange($event)"
      />
    </div>

    <div class="banner-datepicker">
      <Datepicker locale="kr" placeholder="기간 선택" v-model="date" range format="yyyy/MM/dd HH:mm"></Datepicker>
    </div>
    <div class="banner-linkbox">
      <input type="text" placeholder="링크 입력" :value="bannerBoxData.bannerLink" />
    </div>
  </div>
</template>
<script lang="ts">
import { Options, Vue } from "vue-class-component";
import { Prop } from "vue-property-decorator";
import Datepicker from "vue3-date-time-picker";
import "vue3-date-time-picker/dist/main.css";
import axios from "axios";

@Options({
  components: { Datepicker, Prop },
})
export default class BannerAddBox extends Vue {
  @Prop(Number) bannerindex;
  @Prop(Object) bannerBoxData;
  date = [this.bannerBoxData.bannerStart, this.bannerBoxData.bannerEnd];

  deleteBanner(idx) {
    this.$store.commit("REMOVE_BANNER", idx);
  }

  setBannerImg(payload, file) {
    this.$store.commit("SET_BANNER_IMG", payload);

    this.$refs["img" + this.bannerindex].style = "visibility: visible";
  }

  setBannerWord(payload, file) {
    this.$store.commit("SET_BANNER_WORD", payload);

    this.$refs["word" + this.bannerindex].style = "visibility: visible";
  }

  drop(event, type) {
    if (type === "img") {
      this.inputImageDrop(event.dataTransfer.files);
    } else if (type === "word") {
      this.inputWordDrop(event.dataTransfer.files);
    }
  }

  imageUploadChange(event) {
    this.uploadImage(event.target.files[0]);
  }

  wordUploadChange(event) {
    this.uploadWord(event.target.files[0]);
  }

  uploadImage(file) {
    console.log("uploadImage");
    let form = new FormData();
    let idx = this.bannerindex;
    form.append("file", file);
    let payload = {};
    axios
      .post("/api/banner-image", form, {
        headers: { "Content-Type": "multipart/form-data" },
      })
      .then((res) => {
        payload = {
          index: idx,
          src: res.data.path,
        };
        this.setBannerImg(payload, file);
      });
  }

  uploadWord(file) {
    console.log("uploadWord");
    let form = new FormData();
    let idx = this.bannerindex;
    form.append("file", file);
    let payload = {};
    axios
      .post("/api/banner-image", form, {
        headers: { "Content-Type": "multipart/form-data" },
      })
      .then((res) => {
        payload = {
          index: idx,
          src: res.data.path,
        };
        this.setBannerWord(payload, file);
      });
  }

  clickUploadImage() {
    if (this.$store.state.bannerData[this.bannerindex].img != "") {
      return;
    }
    this.$refs["imgupload" + this.bannerindex].click();
  }

  clickUploadWord() {
    if (this.$store.state.bannerData[this.bannerindex].word != "") {
      return;
    }
    this.$refs["wordupload" + this.bannerindex].click();
  }

  inputImageDrop(files) {
    if (this.$store.state.bannerData[this.bannerindex].img != "") {
      return;
    }
    if (files.length) {
      var file = files[0];
      if (!/^image\//.test(file.type)) {
        alert("이미지 파일만 asdasd가능합니다.");
        return false;
      }
      this.uploadImage(file);
    }
  }

  inputWordDrop(files) {
    if (this.$store.state.bannerData[this.bannerindex].word != "") {
      return;
    }
    if (files.length) {
      var file = files[0];
      if (!/^image\//.test(file.type)) {
        alert("이미지 파일만 가능합니다.");
        return false;
      }
      this.uploadWord(file);
    }
  }

  deleteUploadImage(path, target) {
    if(path == "")
      return;
    
    let vm = this;
    let fileName = path.split("/")[4];
    console.log("Delete : " + fileName);
    axios
      .delete("/api/banner-image-temp/"+fileName)
      .then((res) => {
        console.log(res.data);
        if(target === "img"){
          vm.$store.state.bannerData[vm.bannerindex].img = "";
        }else if(target === "word"){
          vm.$store.state.bannerData[vm.bannerindex].word = "";
        }else{
          console.log("[deleteUploadImage] target이 잘못됨.");
        }
      })
      .catch((err) => {
        console.log("deleteUploadImage : "+err);
      });
  }
}
</script>
