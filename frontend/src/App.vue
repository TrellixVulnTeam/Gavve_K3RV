<template>
  <div id="app">
    <router-view />
  </div>
</template>
<script>
export default {
  name: 'app',
  created () {
    document.title = "감귤";
  }
}
</script>
<style lang="stylus">
#app
  font-family Avenir, Helvetica, Arial, sans-serif
  -webkit-font-smoothing antialiased
  -moz-osx-font-smoothing grayscale

html, body
  padding 0
  margin 0
</style>
