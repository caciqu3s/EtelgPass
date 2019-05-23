import React from 'react';
import { View, Text, StyleSheet, Image, Dimensions} from 'react-native';
import { Card, Divider} from 'react-native-elements';
import Icon from 'react-native-vector-icons/MaterialIcons';

export default class Carteirinha extends React.Component {
	
    render() {
		var {height, width} = Dimensions.get('window');
		var mywidth = width;
		var widthMargin = width-32;
    	const { dados, rm } = this.props.screenProps;
		const { Aluno } = dados;
        return(
			 <View style={styles.container}>
            <View style={[styles.box, styles.box1,{width:mywidth}]}></View>
            <View style={[styles.box, styles.box2]}>
			<Text style={styles.textName}>{Aluno.Sala}</Text>
			<View style={styles.avatarContainer}>
			<Image style={styles.avatar} source={{uri: Aluno.URL}}/>
			</View>
           	<Text style={styles.textName}>{Aluno.Nome}</Text>
           	<Text style={styles.textCurso}>{Aluno.Curso}</Text>
           	<Card containerStyle={[styles.cardLayout,{borderRadius:10,width: widthMargin}]}>
			   <View style={styles.cardLayout}>
           	<Text style={styles.textName}>RM</Text>
			   <Divider style={[styles.divisoria,{width:widthMargin-32}]}/>
           	<Text style={styles.textCurso}>{rm}</Text>
           	<Text style={styles.textName}>Número</Text>
		   		<Divider style={[styles.divisoria,{width:widthMargin-32}]}/>
           	<Text style={styles.textCurso}>{Aluno.Número}</Text>
			<Text style={styles.textName}>Validade</Text>
				<Divider style={[styles.divisoria,{width:widthMargin-32}]}/>
           	<Text style={styles.textCurso}>{Aluno.Ano}</Text>
           	<Text style={styles.textName}>Presença</Text>
				<Divider style={[styles.divisoria,{width:widthMargin-32}]}/>
           	<Text style={styles.textCurso}>{Aluno.Porcentagem_Geral}</Text>
			   </View>
           	</Card>
			   </View>
           	</View>
        );
    }
}

const styles = StyleSheet.create({
	cardLayout:{
		justifyContent: 'center',
    	alignItems: 'center'
	},
	textCurso:{
		color: '#808080',
		fontFamily: 'Roboto',
		fontSize: 18,
	},
	divisoria:{
		height:2,
		backgroundColor: '#00c667'
	},
	textName:{
		color:'#000000',
		fontFamily:"Roboto",
		fontSize:26,
		alignSelf: 'center'
	},
	avatarContainer:{
		width:105,
		height:105,
		borderRadius: 50,
		backgroundColor: '#00C667',
		justifyContent: 'center',
    	alignItems: 'center'
	},
 avatar:{
 	width: 100,
 	height: 100,
 	borderRadius: 50,
 },
 container:{
	 flex: 1,
 },
 box1:{
	position: 'absolute',
    height: 131,
	backgroundColor: '#00C667',
 },
 box2:{
	position: 'absolute',
    top: 40,
	transform: [{'translate': [0,0, 1]}],
	justifyContent: 'center',
    alignItems: 'center'
 },
});