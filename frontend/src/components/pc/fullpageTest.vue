<template>
  <div class="container">
    <div class="main">
      <div class="section-box" style="background-color: indianred">
        <h2>Section1</h2>
      </div>
      <br/>
      <div class="section-box" style="background-color: coral">
        <h2>Section2</h2>
      </div>
      <br/>
      <div class="section-box" style="background-color: burlywood">
        <h2>Section3</h2>
      </div>
      <br/>
      <div class="section-box" style="background-color: lightgreen">
        <h2>Section4</h2>
      </div>
      <br/>
      <div class="section-box" style="background-color: cadetblue">
        <h2>Section5</h2>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import {Vue} from "vue-class-component";

export default class HelloWorld extends Vue {

  created() {
    window.addEventListener("wheel", this.wheelHandler);
  }

  unmounted() {
    window.removeEventListener("wheel", this.wheelHandler);
  }

  wheelMove() {
    window.addEventListener("wheel", this.wheelHandler);
  }

  wheelStop() {
    window.removeEventListener("wheel", this.wheelHandler);
  }

  currentPage = 0;
  wheelHandler(e: any) {
    var offsetY = parseInt(e.target.offsetTop);
    var elementHeight = parseInt(e.target.clientHeight);

    if (e.deltaY > 0) { // 휠 내림
      this.scrollTo(offsetY + elementHeight, this.scrollCallback);
    } else if(e.deltaY < 0) { // 휠 올림
      this.scrollTo(offsetY - elementHeight, this.scrollCallback);
    }
    console.log(this.currentPage)
  }

  scrollTo(offset: number, callback: () => void): void {
    console.log("asdasdasd");
    const fixedOffset = offset.toFixed(),
        onScroll = function () {
          if (window.pageYOffset.toFixed() === fixedOffset) {
            window.removeEventListener("scroll", onScroll);
            callback();
          }
        };

    window.addEventListener("scroll", onScroll);
    onScroll();
    window.scrollTo({
      top: offset,
      behavior: "smooth",
    });
  }

  scrollCallback() {
    this.wheelMove();
    console.log("Callback");
  }
}
</script>
<style>
body {
  -ms-overflow-style: none;
  overflow: hidden;
}

::-webkit-scrollbar {
  display: none;
}
</style>
<style scoped lang="stylus">

.container
  width 100%
  height 100%
  text-align center
  background-color lightskyblue

.main
  width 100%
  display inline-block
  height 100%
  background-color mediumpurple

.section-box
  display inline-block
  width 100%
  height 100%
</style>
