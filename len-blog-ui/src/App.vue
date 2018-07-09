<template>
  <div id="app">
    <Layout>
    <!--header begin-->
    <blogHead></blogHead>
    <!--header end-->

    <!--context begin-->
    <router-view/>
    <!--context end-->

    </Layout>
  </div>
</template>

<script>
  import blogHead from "./components/header/head"


export default {
  components:{blogHead},
  name: 'App',
  data(){
    return {}
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #28181d;
  margin-top: 0px;
}
</style>
