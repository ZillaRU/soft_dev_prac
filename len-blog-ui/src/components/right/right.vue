<template>
  <div id="blog-right">
    <div class="c-right">
      <ul>
        <li>
          <Tabs value="name1">
            <TabPane label="标签" name="name1">
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
            <TabPane label="阅读排行" name="name2">标签二的内容</TabPane>
            <TabPane label="标签三" name="name3">标签三的内容</TabPane>
          </Tabs>
        </li>
        <li class="two">
          <Tabs value="name2">
            <TabPane label="推荐" name="name2">
              <Carousel autoplay v-model="value1"
                        :autoplay-speed="speed"

                        loop>
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

             <!-- <Button @click="start">Start</Button>
              <Button @click="finish">Finish</Button>
              <Button @click="error">Error</Button>-->
            </TabPane>
          </Tabs>
        </li>
        <li class="two">
          <Tabs value="name1">
            <TabPane label="关于我" name="name1">
            </TabPane>
          </Tabs>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
    export default {
        name: "blog-right",
      data(){
          return {
            value1:1,
            speed:4000,
          }
      },
      methods:{
        start () {
          this.$Loading.start();
        },
        finish () {
          this.$Loading.finish();
        },
        error () {
          this.$Loading.error();
        }
      }
    }
</script>

<style scoped>
  .demo-carousel {
    height: 200px;
    line-height: 200px;
    text-align: center;
    color: #fff;
    font-size: 20px;
    background: #5fe1ff;
  }
  .c-right{width:35%;height:auto;float:right;}
  .c-right ul li{list-style: none;width:100%;height:auto;min-height:200px;background:#fff;border:0px solid red;margin-top:10px;}

  @media screen and (max-width: 768px) {
    .c-right{width:100%;height:auto;float:right;}
    .c-right ul li{width:100%;height:300px;background:#fff;border:1px solid red;margin-top:10px;}

  }
</style>
