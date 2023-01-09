<template>
    <div class="container">
        <div class="row">
            <div class="col-3">
                <div class="card" style="margin-top: 20px;">
                    <div class="card-body">
                        <img :src="$store.state.user.photo" alt="" style="width: 100%;">
                    </div>
                </div>
            </div>
            <div class="col-9">
                <div class="card" style="margin-top: 20px;">
                    <div class="card-header">
                        <span style="font-size: 130%;font-weight: bold;">My bots</span>
                        <button type="button" class="btn btn-dark float-end" data-bs-toggle="modal"
                            data-bs-target="#add-bot-btn">
                            Create my bot
                        </button>

                        <!-- add Modal -->
                        <div class="modal fade" id="add-bot-btn" tabindex="-1">
                            <div class="modal-dialog modal-xl">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title">Create my bot</h5>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                            aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        <div class="mb-3">
                                            <label for="add-bot-title" class="form-label">Name</label>
                                            <input v-model="botadd.title" type="text" class="form-control"
                                                id="add-bot-title" placeholder="Please enter the name of the bot">
                                        </div>
                                        <div class="mb-3">
                                            <label for="add-bot-description" class="form-label">Description</label>
                                            <textarea v-model="botadd.description" class="form-control"
                                                id="add-bot-description" rows="3"
                                                placeholder="Please enter the description of the bot"></textarea>
                                        </div>
                                        <div class="mb-3">
                                            <label for="add-bot-code" class="form-label">Code</label>
                                            <VAceEditor v-model:value="botadd.content" @init="editorInit" lang="c_cpp"
                                                theme="textmate" style="height: 300px" />

                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <div class="error-message">{{ botadd.error_message }}</div>
                                        <button type="button" class="btn btn-secondary"
                                            data-bs-dismiss="modal">Close</button>
                                        <button type="button" class="btn btn-primary" @click="add_bot">Save
                                            your create</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- add Modal-end -->
                    </div>
                    <div class="card-body">
                        <table class="table table-striped table-hover">
                            <thead>
                                <tr>
                                    <th>Name</th>
                                    <th>Create Time</th>
                                    <th>Manage</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr v-for="bot in bots" :key="bot.id">
                                    <td>{{ bot.title }}</td>
                                    <td>{{ bot.createtime }}</td>
                                    <td>
                                        <button type="button" class="btn btn-outline-secondary"
                                            style="margin-right: 10px;" data-bs-toggle="modal"
                                            :data-bs-target="'#update-bot-modal-' + bot.id">Modify</button>
                                        <button type="button" class="btn btn-outline-danger"
                                            @click="remove_bot(bot)">Delete</button>

                                        <!-- Modify Modal -->
                                        <div class="modal fade" :id="'update-bot-modal-' + bot.id" tabindex="-1">
                                            <div class="modal-dialog modal-xl">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <h5 class="modal-title">Modify my bot</h5>
                                                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                            aria-label="Close"></button>
                                                    </div>
                                                    <div class="modal-body">
                                                        <div class="mb-3">
                                                            <label for="modify-bot-title"
                                                                class="form-label">Name</label>
                                                            <input v-model="bot.title" type="text" class="form-control"
                                                                id="modify-bot-title"
                                                                placeholder="Please enter the name of the bot">
                                                        </div>
                                                        <div class="mb-3">
                                                            <label for="modify-bot-description"
                                                                class="form-label">Description</label>
                                                            <textarea v-model="bot.description" class="form-control"
                                                                id="modify-bot-description" rows="3"
                                                                placeholder="Please enter the description of the bot"></textarea>
                                                        </div>
                                                        <div class="mb-3">
                                                            <label for="modify-bot-code" class="form-label">Code</label>
                                                            <VAceEditor v-model:value="bot.content" @init="editorInit"
                                                                lang="c_cpp" theme="textmate" style="height: 300px" />

                                                        </div>
                                                    </div>
                                                    <div class="modal-footer">
                                                        <div class="error-message">{{ bot.error_message }}</div>
                                                        <button type="button" class="btn btn-secondary"
                                                            data-bs-dismiss="modal">Close</button>
                                                        <button type="button" class="btn btn-primary"
                                                            @click="update_bot(bot)">Save changes</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- Modify Modal-end -->
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import { ref, reactive } from "vue"  //用来绑定对象 
import $ from 'jquery'
import { useStore } from 'vuex'
import { Modal } from 'bootstrap/dist/js/bootstrap'
import { VAceEditor } from 'vue3-ace-editor';
import ace from 'ace-builds';

export default {
    components: {
        VAceEditor
    },
    setup() {
        ace.config.set(
            "basePath", 
            "https://cdn.jsdelivr.net/npm/ace-builds@" + require('ace-builds').version + "/src-noconflict/");

        const store = useStore();
        let bots = ref([]);

        const botadd = reactive({
            title: "",
            description: "",
            content: "",
            error_message: "",
        });

        const refresh_bots = () => {
            $.ajax({
                url: "http://127.0.0.1:3000/user/bot/getlist/",
                type: "GET",
                headers: {
                    Authorization: "Bearer " + store.state.user.token,
                },
                success(resp) {
                    bots.value = resp;
                }
            })
        }
        refresh_bots();

        const add_bot = () => {
            botadd.error_message = "";
            $.ajax({
                url: "http://127.0.0.1:3000/user/bot/add/",
                type: "POST",
                data: {
                    title: botadd.title, //不是ref不加.value
                    description: botadd.description,
                    content: botadd.content,
                },
                headers: {
                    Authorization: "Bearer " + store.state.user.token,
                },//登录之后才能访问的都要加header
                success(resp) {
                    if (resp.error_message === "success") {
                        botadd.title = "";
                        botadd.description = "";
                        botadd.content = "";
                        Modal.getInstance("#add-bot-btn").hide();
                        refresh_bots();
                    } else {
                        botadd.error_message = resp.error_message;
                    }
                }

            })
        }

        const update_bot = (bot) => {
            botadd.error_message = "";
            $.ajax({
                url: "http://127.0.0.1:3000/user/bot/update/",
                type: "POST",
                data: {
                    bot_id: bot.id,
                    title: bot.title, //不是ref不加.value
                    description: bot.description,
                    content: bot.content,
                },
                headers: {
                    Authorization: "Bearer " + store.state.user.token,
                },//登录之后才能访问的都要加header
                success(resp) {
                    if (resp.error_message === "success") {
                        Modal.getInstance('#update-bot-modal-' + bot.id).hide();
                        refresh_bots();
                    } else {
                        botadd.error_message = resp.error_message;
                    }
                }

            })
        }

        const remove_bot = (bot) => {
            $.ajax({
                url: "http://127.0.0.1:3000/user/bot/remove/",
                type: "POST",
                data: {
                    bot_id: bot.id,
                },
                headers: {
                    Authorization: "Bearer " + store.state.user.token,
                },//登录之后才能访问的都要加header
                success(resp) {
                    if (resp.error_message === "success") {
                        refresh_bots();
                    }
                },

            })
        }

        return {
            bots,
            botadd,
            add_bot,
            update_bot,
            remove_bot,
        }
    }
}
</script>

<style scoped>
div.error-message {
    color: red;
}
</style>