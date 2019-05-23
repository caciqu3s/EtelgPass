import React from 'react';
import { StyleSheet, Text, View, KeyboardAvoidingView, ActivityIndicator, Image, ScrollView } from 'react-native';
import axios from 'axios';
import { FormLabel, FormInput, FormValidationMessage } from 'react-native-elements';

import SampleButton from '../components/SampleButton';

export default class LoginPage extends React.Component {
  // Método construtor, usado para criar o state
  constructor(props) {
    super(props);

    this.state ={
      RM: [],
      resultado: [],
      loading: false,
      error: false,
      campo: false,
      message: ''
    };
  }
  
  // Função para Login
  login(RM) {
      this.setState({loading: true});

      // Chamada da API
      axios.get('https://etelg-pass.000webhostapp.com/API-NOTAS/API-ETE/resultadoReact.php?rm=' + RM)
        .then((response) => {

            // Pega o resultado do login feito e coloca no state 'resultado'
            const { Resultado } = response.data;
            this.setState({
                resultado: Resultado,
                loading: false
            });
            
            // Verifica se o login teve erros
            if(this.state.resultado.Aluno == null) {
                this.setState({
                  error: true,
                  message: Resultado.Erro.Erro
                });
            }

            // Caso esteja tudo certo, ele vai para a próxima página
            else {
              this.props.navigation.navigate('Principal', { resultado: this.state.resultado, RM: this.state.RM });
            }
      })
      .catch((error) => {
        this.setState({
          error: true,
          message: error.message,
          loading: false
        });
      });
  }

  // Renderiza uma mensagem de Erro
  renderMessage() {
    if(!this.state.error) {
      return null
    }

    if(this.state.error == true && this.state.campo == false)
    {
      return <FormValidationMessage>{'Campo Obrigatório'}</FormValidationMessage>
    }

    return <FormValidationMessage>{this.state.message}</FormValidationMessage>    
  }

  // Atualiuza componente caso algo for atualizado, no caso está sendo usado para limpar a caixa de texto quando surgir um erro
  componentDidUpdate() {
    if(this.state.error)
    {
      this.input.clearText();
    }
  }

  // renderiza um botão caso não estiver tanto fazer login, caso esteja, exibe uma 'roda' de loading
  renderButton() {
    if(this.state.loading)
    {
      return <ActivityIndicator size='large' />
    }
    
    return <SampleButton
    title="E N T R A R"
    Click={() => {
      if(this.state.campo) {
        this.login(this.state.RM);
      }

      else {
        this.setState({error: true});
      }
    }}
  />
  }

  // Renderiza os componentes gerais da tela
  render() {
    return (
      <View style={styles.container} >
        <ScrollView contentContainerStyle={styles.containerContent}>
        <KeyboardAvoidingView  behavior="position" enabled>
          
          <Image style={styles.image}
                source={require('../images/logo.png')}/>
          
          <Text style={styles.title}>Bem Vindo(a) ao EtelgPass</Text>
          <Text style={styles.subtitle}>Faça Login para continuar</Text>

          <FormLabel >Insira seu RM ou RA</FormLabel>
          <FormInput inputStyle={styles.formInput} ref={input => this.input = input} onChangeText={(rm) => {
              if(rm.length > 0) {
                this.setState({RM: rm, campo: true, error: false});
              }

              else {
                this.setState({campo: false});
              }
            }} onSubmitEditing={() => {
              if(this.state.campo) {
                this.login(this.state.RM);
              }
        
              else {
                this.setState({error: true});
              }
            }} returnKeyType={'done'}/>

          {this.renderMessage()}
        </KeyboardAvoidingView>

        {this.renderButton()}
      </ScrollView>
      </View>
    );
  }
}

// Constante de estilo
const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
  },

  containerContent: {
    flexGrow: 1,
    justifyContent: 'center'
  },

  title: {
    fontSize: 22,
    color: '#00c667',
    fontWeight: 'bold',
    alignSelf: 'center'
  },

  subtitle: {
    alignSelf: 'center',
    marginBottom: 60,
    fontWeight: 'bold'
  },

  error: {
    color: '#d80d0d'
  },

  image:{
    aspectRatio: 1,
    height: 150,
    marginLeft: 30,
    alignSelf: 'center'
  },

  formInput: {
    color: '#000000',
    marginBottom: 15
  }
});
