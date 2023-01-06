<template>
  <NavBar />
  <router-view></router-view>
</template>

<script>
import NavBar from './components/NavBar.vue'
import "bootstrap/dist/css/bootstrap.min.css"
import "bootstrap/dist/js/bootstrap"
//import $ from 'jquery'

export default {
  components: {
    NavBar
  },
  setup() {
    // $.ajax({
    //   url: "http://127.0.0.1:3000/user/account/token/",
    //   type: "post",
    //   data: {
    //     username: "cxh",
    //     password: "123123",
    //   },
    //   success(resp) {
    //     console.log(resp);

    //   },
    //   error(resp) {
    //     console.log(resp);
    //   }
    // });

    // $.ajax({
    //   url:"http://127.0.0.1:3000/user/account/info/",
    //   type:"get",
    //   headers:{
    //     Authorization: "Bearer " + "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI2NTBmNWY4YWRhZTI0Y2MxOWE5NzhhMzA5OGEzNzgyNiIsInN1YiI6IjEiLCJpc3MiOiJzZyIsImlhdCI6MTY3Mjg4ODU4OSwiZXhwIjoxNjc0MDk4MTg5fQ.l4FBOS1A77s_2S4J28PdKu3-C9Ec2xR_ANV2SSGe4RU"
    //   },
    //   success(resp){
    //     console.log(resp);
    //   },
    //   error(resp){
    //     console.log(resp);
    //   }
    // });

    // $.ajax({
    //   url: "http://127.0.0.1:3000/user/account/register/",
    //   type: "post",
    //   data:{
    //     username:"2023",
    //     password:"123123",
    //     confirmedPassword:"123123",
    //   },
    //   success(resp){
    //     console.log(resp);
    //   },
    //   error(resp){
    //     console.log(resp);
    //   }
    // });
  }
}
</script>

<style>
body {
  background-image: url("@/assets/images/background.png");
  background-size: cover;
}
</style>
