import React from 'react';
import { FlatList, StyleSheet, View } from 'react-native';
import CardNotas from '../components/CardNotas';

export default class Notas extends React.Component {   
    renderNotas() {
        const { dados } = this.props.screenProps;
        const Notas = dados.Notas;
        
        return (
            <FlatList
                style={styles.container}
                data={Notas}
                renderItem={({ item }) => (
                    <CardNotas notas={item} />
                )}
                keyExtractor={(item) => item.disciplina} />
        );

    }
    
    render() {
        return (
            <View style={styles.master}>
                <View style={styles.container}>
                    {this.renderNotas()}
                </View>
            </View> 
        );
    }
}

const styles = StyleSheet.create({
    container: {
        marginTop: 15,
        marginBottom: 5
    },

    master: {
        backgroundColor: '#dcdee2'
    }
});