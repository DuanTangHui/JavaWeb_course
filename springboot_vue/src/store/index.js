import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    user_name:sessionStorage.getItem('user_name')||'?',
    role:sessionStorage.getItem('role')||'?',
    user_id:sessionStorage.getItem('user_id')||'?'
  },
  getters:{
    getUserName:state => {
      return state.user_name
    },
    getRole:state => {
      return state.role
    },
    getUserId:state => {
      return state.user_id
    }
  },
  mutations: {
    setUserName(state,user_name) {
      state.user_name = user_name;
      sessionStorage.setItem('user_name', user_name);
    },
    setRole(state,role){
      state.role = role;
      sessionStorage.setItem('role',role);
    },
    setUserId(state,user_id){
      state.user_id = user_id;
      sessionStorage.setItem('user_id',user_id);
    }
  },
  actions: {
    setUserNameAsy(context,user_name){
      context.commit('setUserName',user_name)
    },
    setRoleAsy(context,role){
      context.commit('setRole',role)
    },
    setUserIdAsy(context,user_id){
      context.commit('setUserId',user_id)
    },
  },
  modules: {
  }
})
