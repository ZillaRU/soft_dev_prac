<template>
  <div id="home">
    {{route}}
    <button value="" @click="index()">主页</button>
  </div>
</template>

<script>
    export default {
        name: "home",
      data(){
          return{}
      },
      computed:{
          route:function(){
            return this.$route.params.id
          }
      },
      methods:{
        index:function () {
          this.$router.push('/');
        }
      }
    }
</script>

<style scoped>

</style>
