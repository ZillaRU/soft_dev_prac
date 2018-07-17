<template>
  <div id="head">
    <Row class-name="title-row" align="middle" justify="center">
      <Col :xs="24" :sm="4" :lg="8">
        <Icon type="ios-flame" size="50" />
      </Col>
      <Col :xs="24" :sm="14" :lg="10">
        <Menu  class="aa" mode="horizontal" theme="light" active-name="1">
          <MenuItem name="1">
            <router-link to="/blogHead">
              <div>
                <Icon type="ios-paper"></Icon>
                java
              </div>
            </router-link>
          </MenuItem>
          <MenuItem name="2">
            <Icon type="ios-people"></Icon>
            架构
          </MenuItem>
          <MenuItem name="3">
            <Icon type="ios-people"></Icon>
            Linux
          </MenuItem>
          <MenuItem name="4">
            <Icon type="ios-people"></Icon>
            其他
          </MenuItem>
        </Menu>
      </Col>
      <Col :xs="24" :sm="4" :lg="6">
        <div style="margin-top: 15px">
        <Button type="ghost">登录</Button>
        </div>
      </Col>
    </Row>
  </div>
</template>

<script>
    export default {
        name: "head",
      data(){
        return {
          a:1,
          theme1: 'light'
        }
      },
      methods:{
        toIndex(){
          this.$router.push('/');
        },
        init(){
          this.$http.get('api/blog/header').then(response => {
            // console.log(response);
          }, response => {

          });
        }
      },
    mounted(){
      this.init()
    }
    }
</script>

<style scoped>
  .title-row{
    background: white;
    border-bottom: 0px solid red;
  }
</style>
