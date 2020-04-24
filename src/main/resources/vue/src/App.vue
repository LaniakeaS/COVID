<template>

  <div id="app">
 // Add a link to go to a new webpage
    <div class="linkBar">
      <router-link class="mapW" to="/mapW">Epidemic Map</router-link>
    </div>

    <div class="linkBar">

      <router-view></router-view>
    </div>

  </div>

</template>

<style>

  /* #app{

    display: flex;

    justify-content: center;

    align-items: center;

    flex-direction: column;

  } */

  #app {

    text-align: center;

  }

  /* .map{

   color: #ccc;

   font-size: 20px;

   margin: 0 auto;

   text-align: center;

  } */

  .linkBar{

    margin: 20px 0;

    font-size: 26px;

  }

  .router-link-active{

    color: rgb(96, 174, 138);

  }

</style>
