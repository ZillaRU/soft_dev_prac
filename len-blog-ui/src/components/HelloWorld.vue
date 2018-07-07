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


  <Row class-name="title-row">
    <Col :xs="20" :sm="4" :lg="10">
      <Icon type="ios-flame" size="50" />
    </Col>
    <Col :xs="16" :sm="16" :lg="14">
     <Menu mode="horizontal" :theme="theme1" active-name="1">
        <MenuItem name="1">
          <Icon type="ios-paper"></Icon>
          java
        </MenuItem>
        <MenuItem name="2">
          <Icon type="ios-people"></Icon>
          架构
        </MenuItem>
        <MenuItem name="3">
          <Icon type="ios-people"></Icon>
          其他
        </MenuItem>
      </Menu>
    </Col>
  </Row>
  <div class="cont">
    <div class="content clearfix">
      <div class="left">
        <ul>

          <li class="one">
            <Card style="height: 200px">
            <h3>lenos快速开发脚手架</h3>
            <p class="time">作者：<span>zxm</span> • 2017-10-31 10:05:47</p>
            <p class="cot">lenos快速开发脚手架</p>
            <div class="pic">
              <img src="../assets/aa.jpeg"/>
            </div>
          </Card>
          </li>


          <li class="one">
            <Card style="height: 200px">
            <h3>lenos快速开发脚手架</h3>
            <p class="time">作者：<span>zxm</span> • 2017-10-31 10:05:47</p>
            <p class="cot">lenos快速开发脚手架</p>
            <div class="pic">
              <img src="../assets/aa.jpeg"/>
            </div>
            </Card>
          </li>

          <li class="one">
            <Card style="height: 200px">
            <h3>lenos快速开发脚手架</h3>
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
      <div class="c-right">
        <ul>
          <li>
            <Tabs value="name1">
              <TabPane label="标签一" name="name1">
                <Tag checkable  color="blue">java</Tag>
                <Tag checkable  color="green">spring</Tag>
                <Tag checkable  color="red">vue</Tag>
                <Tag checkable  color="yellow">batis</Tag>
                <Tag checkable  color="blue">标签一</Tag>
                <Tag checkable  color="green">标签二</Tag>
                <Tag checkable  color="red">标签三</Tag>
                <Tag checkable  color="yellow">标签四</Tag>
                <Tag checkable  color="blue">标签一</Tag>
                <Tag checkable  color="green">标签二</Tag>
                <Tag checkable  color="red">标签三</Tag>
                <Tag checkable  color="yellow">标签四</Tag>
              </TabPane>
              <TabPane label="标签二" name="name2">标签二的内容</TabPane>
              <TabPane label="标签三" name="name3">标签三的内容</TabPane>
            </Tabs>
          </li>
          <li class="two">
            <Tabs value="name1">
              <TabPane label="最新" name="name1">
                <Carousel v-model="value1" loop>
                  <CarouselItem>
                    <div class="demo-carousel">1</div>
                  </CarouselItem>
                  <CarouselItem>
                    <div class="demo-carousel">2</div>
                  </CarouselItem>
                  <CarouselItem>
                    <div class="demo-carousel">3</div>
                  </CarouselItem>
                  <CarouselItem>
                    <div class="demo-carousel">4</div>
                  </CarouselItem>
                </Carousel>
              </TabPane>
            </Tabs>

            <Button @click="start">Start</Button>
            <Button @click="finish">Finish</Button>
            <Button @click="error">Error</Button>
          </li>
          <li class="two"></li>
        </ul>
      </div>
    </div>
  </div>
  <Footer style="height: 10px;border:1px solid red; color: white" width="auto"></Footer>
  </Layout>
  <BackTop></BackTop>
</div>
</template>


<script>
  import titleItem from "../components/item/titleItem"
export default {
  components: {
      titleItem
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
      value1:0
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
    test:function(){
     this.msg='zxm111'
    },
    //计算属性 第二种 用方法来替代
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
  .title-row{
    background: white;
    border-bottom: 0px solid red;
  }
  #HelloWorld{
    background: #ebebeb;
  }
  .content{

  }
  .col{
    border:1px solid black;
  }
  .demo-carousel {
    height: 200px;
    line-height: 200px;
    text-align: center;
    color: #fff;
    font-size: 20px;
    background: #5fe1ff;
  }

  .cont {width:100%;height:auto;background: rgba(255, 255, 255, 0.12);}
  .cont .left{width:62%;height:auto;float:left;list-style: none;}
  .cont .left ul li{width:100%;height:200px;background:#fff;margin-top:10px;border:0px solid red;}
  .cont .left ul li h3{font-size:20px;color:#f4645f;text-align:left;margin-left:30px;margin-top:30px;}
  .cont .left ul li h3:hover{cursor:pointer;text-decoration: underline;}
  .cont .left ul li p.time{text-align:left;margin-left:30px;margin-top:5px;font-size:12px;color:#333;}
  .cont .left ul li p.time span{color:#f4645f;}
  .cont .left ul li p.time span:hover{cursor:pointer;text-decoration: underline;}
  .cont .left ul li p.cot{text-align:left;margin-left:30px;margin-top:20px;font-size:14px;color:#333;}
  .cont .left ul li .pic{width:150px;height:150px;border:0px solid red;float:right;margin-top:-120px;margin-right:30px;}
  .cont .left ul li .pic img{width:150px;height:auto;}
  .cont .content{width:75%;height:auto;margin:30px auto;border:0px solid red;}
  .cont .c-right{width:35%;height:auto;float:right;}
  .cont .c-right ul li{width:100%;height:auto;min-height:200px;background:#fff;border:0px solid red;margin-top:10px;}
  .clearfix:after{
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
  @media screen and (max-width: 960px) {
    .wrapper { width: 93.75%; /* 960px/1024px  */ margin: 0 auto; }
    .websiteDescription { margin-left: 0; }
  }
  /* for 768px or less */
  @media screen and (max-width: 768px) {
    .mainContent { float: none; width: auto; }
    .sidebar { margin: 0; }
    h1#logo { float: none }
    nav { float: none; }
    nav li { display: block; list-style: none; padding: 1px; text-align: center; }
    nav li a { display: block; }
  }
  /* for 480px or less */
  @media screen and (max-width: 768px) {
    h1#logo { float: none }
    .cont {width:100%;height:auto;background:#ececec;}
    .cont .left{width:100%;height:auto;float:left;list-style: none;}
    .cont .left ul li{width:100%;height:200px;background:#fff;border:1px solid red;}
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
    .cont .c-right{width:100%;height:auto;float:right;}
    .cont .c-right ul li{width:100%;height:300px;background:#fff;border:1px solid red;margin-top:10px;}
    /*.cont .c-right ul li.two{margin-top:10px;}*/
  }
  @media (min-width: 981px) {
    .websiteDescription { margin-left: 400px }
    .bloglist figure { float: left; width: 233px; margin-right: 20px; margin-bottom: 10px; }
    .bloglist figure img { padding: 4px; border: #f4f2f2 1px solid; width: 225px; }
  }



</style>
