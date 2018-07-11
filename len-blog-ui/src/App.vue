<template>
  <div id="app">
    <Layout>
      <!--header begin-->
      <blogHead></blogHead>
      <!--header end-->

      <!--context begin-->
      <div class="cont">
        <div class="content clearfix">
          <div class="blog-left">
            <router-view/>
          </div>
          <blogRight></blogRight>
        </div>
      </div>
      <!--right begin-->

      <!--right end-->
      <!--context end-->

      <blog-bottom></blog-bottom>
    </Layout>
    <BackTop></BackTop>
  </div>
</template>

<script>
  import blogHead from "./components/header/head"
  import blogRight from "./components/right/right"
  import blogBottom from "./components/bottom/bottom"


  export default {
    components: {
      blogHead,
      blogRight,
      blogBottom
    },
    name: 'App',
    data() {
      return {}
    }
  }
</script>

<style>
  @import '../static/css/blog-index.css'; /*引入公共样式*/
  #app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #28181d;
    margin-top: 0px;
  }
</style>
