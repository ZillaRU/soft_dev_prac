<template>
  <div id="home" class="blog-home">

          <!--文章详细开发-->
          <div class="title-article">
            <h3>

              lenos开源脚手架啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊阿啊啊啊啊啊阿啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊

            </h3>
          </div>

          <p data-v-656039f0="" class="time">作者：<span data-v-656039f0="">zxm</span> • 2017-10-31 10:05:47</p>
          <div class="text-context">
          <ul>
            <li>
              <h3>1、查看作者</h3>
              <pre>
export default new Router({
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld
    },
    {
      path:'/home/:id',
      component:Home,
      children:[
        {
          path: 'person',
          component: Person
        }
      ]
    }
  ]
})
              </pre>
            </li>
            <li>
              <h3>2、作者是怎么炼成的</h3>
              <pre>
routes: [
{
path: '/',
name: 'HelloWorld',
component: HelloWorld
}
              </pre>
            </li>
            <li>
              <h3>3、作者是怎么炼成的！！作者是怎么炼成的！！</h3>
              <pre>
path:'/home/:id',
 component:Home,
 children:[
 {
 path: 'person',
 component: Person
              </pre>
            </li>
            <li class="link">
              <p>
                <span>上一篇：</span><a href="#">作者是怎么炼成的</a>
              </p>
              <p>
                <span>下一篇：</span><a href="#">作者是怎么成仙的</a>
              </p>
            </li>
          </ul>
          </div>
        <!--评论-->
          <div class="comment">
            <h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;最新评论</h5>
            <div class="com clearfix">
              <div class="pic"></div>
              <div class="tit">
                <ul>
                  <li>作者你好:谢谢作者</li>
                  <li>时间：2018-05-26 <a>回复</a></li>
                </ul>
              </div>
            </div>
            <div class="answer">
              <textarea placeholder="这是帮助文字">

              </textarea>
              <input type="submit" value="提交评论"/>
            </div>
          </div>
          <!--评论-->

  </div>
</template>

<script>
  import blogHead from "../components/header/head"
  import blogRight from "../components/right/right"
  import blogBottom from "../components/bottom/bottom"

  export default {
    components: {
      blogHead,
      blogRight,
      blogBottom
    },
    name: "home",
    data() {
      return {}
    },
    computed: {
      route: function () {
        return this.$route.params.id
      }
    },
    methods: {
      index: function () {
        this.$router.push('/');
      }
    }
  }
</script>

<style scoped>
  .blog-home{background:#fff;margin-top:10px;}
  h3{font-size:28px;color:#333;font-weight:normal;padding-top:30px;}
  p.time{font-size: 14px;color: #999;}
  p.time span{color:#f4645f;}
  p.time span:hover{text-decoration: underline;cursor:pointer;}
  ul{margin-top:20px;list-style:none;}
  ul li{width:90%;margin:0 auto;height:auto;border:0px solid red;}
  ul li h3{font-size:24px;color:#333;text-align:left;}
  ul li pre{display: block;padding:20px;margin-top:10px;font-size: 16px;line-height: 1.42857143;color: #666;word-break: break-all;word-wrap: break-word;background: rgba(255, 255, 255, 0.12);border: 1px solid #ccc;border-radius: 4px;text-align:left;}
  ul li.link{font-size:14px;text-align:left;margin-top:20px;}
  ul li.link p{margin-top:10px;}
  ul li.link a:hover{text-decoration: underline;}
  .comment h5{height:50px;border-bottom:1px solid #f4645f;line-height:65px;text-align:left;font-size:16px;font-weight:normal;color:#f4645f;}
  .comment .com{width:90%;height:auto;border:0px solid red;margin:0px auto;margin-top:15px;}
  .comment .com .pic{width:60px;height:60px;float:left;background:#ececec;}
  .comment .com .tit ul{margin-top:0;}
  .comment .com .tit ul li{float:right;width:85%;font-size:14px;color:#333;text-align:left;margin-top:5px;}
  .comment .com .tit ul li a{color:#f4645f;text-decoration: underline;}
  .comment .answer{width:90%;height:auto;border:0px solid red;margin:0 auto;}
  .comment .answer textarea{width:100%;height:auto;padding:10px;margin-top:10px;color:#999;font-size:14px;border:1px solid #f4645f;outline:none;}
  .comment .answer input{width:80px;height:40px;background:#f4645f;font-size:14px;color:#fff;float:right;
    outline: none;border:none;cursor:pointer;}

  .title-article{
    width: 95%;
    font-size: 24px;
    word-break: break-all;
    margin:0 auto;
  }

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
  @media screen and (max-width: 768px) {
    h3 {
      font-size: 28px;
      color: #333;
      font-weight: normal;
      padding-top: 20px;
    }

    p.time {
      font-size: 14px;
      color: #999;
    }

    p.time span {
      color: #f4645f;
    }

    .cont .left p.time span:hover {
      text-decoration: underline;
      cursor: pointer;
    }

    ul {
      margin-top: 20px;
      list-style: none;
    }

    ul li {
      width: 90%;
      margin: 0 auto;
      height: auto;
      border: 0px solid red;
    }

    ul li h3 {
      font-size: 24px;
      color: #333;
      text-align: left;
    }

    ul li pre {
      display: block;
      padding: 20px;
      margin-top: 10px;
      font-size: 16px;
      line-height: 1.42857143;
      color: #666;
      word-break: break-all;
      word-wrap: break-word;
      background: rgba(255, 255, 255, 0.12);
      border: 1px solid #ccc;
      border-radius: 4px;
      text-align: left;
    }
    ul li.link{font-size:14px;text-align:left;margin-top:20px;}
     ul li.link p{margin-top:10px;}
    ul li.link a:hover{text-decoration: underline;}
    .comment h5{height:50px;border-bottom:1px solid #f4645f;line-height:65px;text-align:left;font-size:16px;font-weight:normal;color:#f4645f;}
    .comment .com{width:90%;height:auto;border:0px solid red;margin:0px auto;margin-top:15px;}
    .comment .com .pic{width:60px;height:60px;float:left;background:#ececec;}
    .comment .com .tit ul{margin-top:0;}
    .comment .com .tit ul li{float:right;width:80%;font-size:14px;color:#333;text-align:left;margin-top:5px;}
    .comment .com .tit ul li a{color:#f4645f;text-decoration: underline;}
    .comment .answer{width:90%;height:auto;border:0px solid red;margin:0 auto;}
    .comment .answer textarea{width:100%;height:auto;padding:10px;margin-top:10px;color:#999;font-size:14px;border:1px solid #f4645f;outline:none;}
    .comment .answer input{width:80px;height:40px;background:#f4645f;font-size:14px;color:#fff;float:right;
      outline: none;border:none;cursor:pointer;}





  }





</style>

