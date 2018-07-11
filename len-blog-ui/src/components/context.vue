<template>
<div id="context" class="blog-context">
  <!--{{msg}}<br/>
  {{ 1 + 1 }}<br/>
  {{name()}}<br/>
  {{message}}<br/>
  <input v-model="message" placeholder="edit me">

  <button value="" @click="selectHome()">路由</button>-->
    <!--<Header width="auto" style="height: 5px">

    </Header>-->

      <ul>

          <li class="one">
            <Card style="height: 200px">
              <div class="blog-title" @click="detail">
            <h3>lenos快速开发脚手架lenos啊啊啊啊啊啊啊啊......</h3>
              </div>
            <p class="time">作者：<span>zxm</span> • 2017-10-31 10:05:47</p>
            <p class="cot">lenos快速开发脚手架</p>
            <div class="pic">
              <img src="../assets/aa.jpeg"/>
            </div>
          </Card>
          </li>



          <li class="one">
            <Card style="height: 200px">
              <div @click="detail">
            <h3>lenos快速开发脚手架</h3>
              </div>
            <p class="time">作者：<span>zxm</span> • 2017-10-31 10:05:47</p>
            <p class="cot">lenos快速开发脚手架</p>
            <div class="pic">
              <img src="../assets/aa.jpeg"/>
            </div>
            </Card>
          </li>

          <li class="one">
            <Card style="height: 200px">
              <div @click="detail">
            <h3>lenos快速开发脚手架</h3>
              </div>
            <p class="time">作者：<span>zxm</span> • 2017-10-31 10:05:47</p>
            <p class="cot">lenos快速开发脚手架</p>
            <div class="pic">
              <img src="../assets/aa.jpeg"/>
            </div>
            </Card>
          </li>

        </ul>
        <Page :total="40" size="small" show-total></Page>


</div>
</template>


<script>


  export default {

  name: 'context',
  data (){
    return {
      msg: 'zxm',
      message:[],
      datas:[
        {
          id:1,
          name:'标题1'
        },{
          id:2,
          name:'标题2'
        }
      ],
      value1:0,
    }
  },
  created:function(){
    console.log(this.msg);
  },
  computed:{
    //计算属性 第一种 用属性来替代
    //计算属性是基于它们的依赖进行缓存的
    //
    /*name:function(){
      // return this.msg.split('').reverse().join('');
      return Date.now();
    }*/
  },

  methods:{
    detail(){
      this.$router.push('/home/11111')
    },
    test:function(){
     this.msg='zxm111'
    },
    //计算属性 第二种 用方法来替代`
    //如果你不希望有缓存，请用方法来替代。
    name:function(){
     // return 'zxm'//.split('').reverse().join('');
      return Date.now();
    },
    selectHome:function(){
      console.log(this.message);
      this.$router.push('/home/'+this.message);
    }
  },

  //侦听属性
  watch:{
    /*msg:function(val){
      this.name=this.msg+'aaa';
    }*/
  }
}
</script>
<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  @import '../../static/css/blog-context.css';



</style>
