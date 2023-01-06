<template>
    <ContentField v-if="!$store.state.user.pulling_info">
        <div class="row justify-content-md-center">
            <div class="col-3">
                <form @submit.prevent="login">
                    <div class="mb-3">
                        <label for="username" class="form-label">Username</label>
                        <input v-model="username" type="text" class="form-control" id="username"
                            placeholder="Please Input Your Username">
                    </div>
                    <div class="mb-3">
                        <label for="password" class="form-label">Password</label>
                        <input v-model="password" type="password" class="form-control" id="password"
                            placeholder="Please Input Your Password">
                    </div>
                    <div class="error-message">{{ error_message }}</div>
                    <button type="submit" class="btn btn-primary">Submit</button>
                </form>
            </div>
        </div>
    </ContentField>
</template>


<script>
import ContentField from "../../../components/ContentField.vue"
import { useStore } from 'vuex'
import { ref } from 'vue'
import router from "../../../router/index"

export default {
    components: {
        ContentField
    },
    setup() {
        const store = useStore();
        let username = ref('');
        let password = ref('');
        let error_message = ref('');

        const jwt_token = localStorage.getItem("jwt_token");
        if (jwt_token) {
            //调用mutation需要commit（同步操作） action里需要dispatch (异步操作，要从云端拉取)
            store.commit("updateToken", jwt_token);
            //从云端获取用户信息对比
            store.dispatch("getinfo", {
                success() {
                    //刷新后直接跳到home页面
                    router.push({ name: "home"});
                    store.commit("updatePullingInfo", false);
                },
                error() {
                    store.commit("updatePullingInfo", false);
                }
            })
        } else {
            store.commit("updatePullingInfo", false);
        }

        
        const login = () => {
            error_message.value = "";
            //调用user.js 里 action的函数
            store.dispatch("login", {
                username: username.value,
                password: password.value,

                success() {
                    store.dispatch("getinfo", {
                        success() {
                            router.push({ name: 'home' });
                            console.log(store.state.username);
                        }
                    })
                },
                error() {
                    error_message.value = "wrong username or password";
                }
            })
        }

        return {
            username,
            password,
            error_message,
            login,
        }
    }
}
</script>

<style scoped>
button {
    width: 100%;
}

div.error-message {
    color: red;
}
</style>