<template>
<div ref = "parent" class="gamemap">
    <canvas ref = "canvas" tabindex="0"></canvas>
</div>
</template>

<script>
import { GameMap } from '@/assets/scripts/GameMap';
import { ref, onMounted } from 'vue';  //onMounted指组件挂载完需要执行哪些参数
import {useStore} from 'vuex';

export default {
    setup() {   //setup ()可能会存在定义域的问题？？
        const store = useStore();
        let parent = ref(null);//setup: () =>这样写就不会重新绑定this
        let canvas = ref(null);

        onMounted(() => {
            new GameMap(canvas.value.getContext('2d'), parent.value, store) //canvas.value.getContext 取得这个canvas element的context
        });

        return {     //返回后才能在template里用
            parent, 
            canvas
        }
    }
}
</script>

<style scoped>
div.gamemap{
    width: 100%;
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
}
</style>