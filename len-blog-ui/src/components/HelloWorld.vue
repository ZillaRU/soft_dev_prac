<template>
<div id="HelloWorld" class="layout">
  <!--{{msg}}<br/>
  {{ 1 + 1 }}<br/>
  {{name()}}<br/>
  {{message}}<br/>
  <input v-model="message" placeholder="edit me">

  <button value="" @click="selectHome()">路由</button>-->
  <Layout>
    <!--<Header width="auto" style="height: 5px">

    </Header>-->
    <blogHead></blogHead>

  <div class="cont">
    <div class="content clearfix">
      <div class="left">
      <!--  <ul>

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

        </ul>-->
        <Page :total="40" size="small" show-total></Page>
      </div>
      <blogRight></blogRight>

    </div>
  </div>
    <blog-bottom></blog-bottom>
  </Layout>
  <BackTop></BackTop>
</div>
</template>


<script>
  import titleItem from "../components/item/titleItem"
  import blogHead from "../components/header/head"
  import blogRight from "../components/right/right"
  import blogBottom from "../components/bottom/bottom"

  export default {
  components: {
      titleItem,
      blogHead,
      blogRight,
    blogBottom
    },
  name: 'HelloWorld',
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
    },
    start () {
      this.$Loading.start();
    },
    finish () {
      this.$Loading.finish();
    },
    error () {
      this.$Loading.error();
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
  #HelloWorld{
    background: #ebebeb;
  }
  .content{

  }
  .col{
    border:1px solid black;
  }


  .cont {width:100%;height:auto;background: rgba(255, 255, 255, 0.12);}
  .cont .left{width:62%;height:auto;float:left;list-style: none;}
  .cont .left ul li{width:100%;height:200px;background:#fff;margin-top:10px;border:0px solid red;list-style: none;}
  .cont .left ul li h3{font-size:20px;color:#f4645f;text-align:left;margin-left:30px;margin-top:30px;}
  .cont .left ul li h3:hover{cursor:pointer;text-decoration: underline;}
  .cont .left ul li p.time{text-align:left;margin-left:30px;margin-top:5px;font-size:12px;color:#333;}
  .cont .left ul li p.time span{color:#f4645f;}
  .cont .left ul li p.time span:hover{cursor:pointer;text-decoration: underline;}
  .cont .left ul li p.cot{text-align:left;margin-left:30px;margin-top:20px;font-size:14px;color:#333;}
  .cont .left ul li .pic{width:150px;height:150px;border:0px solid red;float:right;margin-top:-120px;margin-right:30px;}
  .cont .left ul li .pic img{width:150px;height:auto;}
  .cont .content{width:75%;height:auto;margin:30px auto;border:0px solid red;}
  /*.cont .c-right{width:35%;height:auto;float:right;}
  .cont .c-right ul li{list-style: none;width:100%;height:auto;min-height:200px;background:#fff;border:0px solid red;margin-top:10px;}
 */ .clearfix:after{
    content:"";
    height:0;
    line-height:0;
    display:block;
    clear:both;
    visibility:hidden;
  }
  .clearfix{
    zoom:1;
  }
 /* @media screen and (max-width: 960px) {
    .wrapper { width: 93.75%; !* 960px/1024px  *! margin: 0 auto; }
    .websiteDescription { margin-left: 0; }
  }
  !* for 768px or less *!
  @media screen and (max-width: 768px) {
    .mainContent { float: none; width: auto; }
    .sidebar { margin: 0; }
    h1#logo { float: none }
    nav { float: none; }
    nav li { display: block; list-style: none; padding: 1px; text-align: center; }
    nav li a { display: block; }
  }*/
  /* for 480px or less */
  @media screen and (max-width: 768px) {
    h1#logo { float: none }
    .cont {width:100%;height:auto;background:rgba(255, 255, 255, 0.12);}
    .cont .left{width:100%;height:auto;float:left;list-style: none;}
    .cont .left ul li{width:100%;height:200px;background:#fff;border:1px solid red;list-style: none;}
    .cont .left ul li.two{margin-top:10px;}
    .cont .left ul li h3{font-size:20px;color:#f4645f;text-align:left;margin-left:10px;margin-top:50px;}
    .cont .left ul li h3:hover{cursor:pointer;text-decoration: underline;}
    .cont .left ul li p.time{text-align:left;margin-left:10px;margin-top:5px;font-size:12px;color:#333;}
    .cont .left ul li p.time span{color:#f4645f;}
    .cont .left ul li p.time span:hover{cursor:pointer;text-decoration: underline;}
    .cont .left ul li p.cot{text-align:left;margin-left:10px;margin-top:20px;font-size:14px;color:#333;}
    .cont .left ul li .pic{width:110px;height:110px;border:0px solid red;float:right;margin-top:-100px;margin-right:10px;}
    .cont .left ul li .pic img{width:110px;height:110px;}
    .cont .content{width:100%;height:auto;margin:40px auto;border:1px solid red;}
    /*.cont .c-right{width:100%;height:auto;float:right;}
    .cont .c-right ul li{width:100%;height:300px;background:#fff;border:1px solid red;margin-top:10px;}*/
    /*.cont .c-right ul li.two{margin-top:10px;}*/
  }
  @media (min-width: 981px) {
    /*.websiteDescription { margin-left: 400px }*/
    .bloglist figure { float: left; width: 233px; margin-right: 20px; margin-bottom: 10px; }
    .bloglist figure img { padding: 4px; border: #f4f2f2 1px solid; width: 225px; }
  }



</style>
