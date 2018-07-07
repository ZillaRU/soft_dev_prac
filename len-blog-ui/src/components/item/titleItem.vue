<template>
  <div id="item">
  <Card style="width:10%;margin-top:10px;">
    <div style="text-align:center">
      {{item}}
    </div>

  </Card>
  </div>
</template>

<script>
    export default {
        props:['item'],
      name:'item',
      /*data(){
          return {
            items:this.item
          }
      }*/
    }
</script>

<style scoped>

</style>
