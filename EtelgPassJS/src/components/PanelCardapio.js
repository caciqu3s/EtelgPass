import React from 'react';
import { View, Text, StyleSheet } from 'react-native';
import Panel from 'react-native-panel';
import { Divider } from 'react-native-elements'
import LineCardapio from './LineCardapio';

const PanelCardapio = (props) => {
    const { cardapio } = props;

    return (
        <View style={styles.container}>
            <Panel style={styles.panel} header={() => { return (
                <View style={styles.containerText}>
                    <Text style={styles.text}>{cardapio.Dia_Semana}</Text>
                </View>
            );}} >
                {/* todo o conteudo do panel*/}
                <View>
                    <Text style={styles.title}>Restaurante (Gratuito)</Text>
                    <Divider style={styles.divider}/>
                    <LineCardapio title={'Prato Básico 1'} content={cardapio.Opção_1} />
                    <LineCardapio title={'Prato Básico 2'} content={cardapio.Opção_2} />
                    <LineCardapio title={'Prato Principal'} content={cardapio.Opção_3} />
                    <LineCardapio title={'Guarnição'} content={cardapio.Opção_4} />
                    <LineCardapio title={'Sobremesa / Salada'} content={cardapio.Opção_1} />

                    <Divider />
                    
                    <Text style={styles.title}>Restaurante (APM)</Text>
                    <Divider style={styles.divider}/>
                    <LineCardapio title={'Prato Básico'} content={cardapio.Prato_Básico} />
                    <LineCardapio title={'Salada'} content={cardapio.Salada} />
                    <LineCardapio title={'Prato Principal'} content={cardapio.Prato_Principal} />
                    <LineCardapio title={'Guarnição'} content={cardapio.Guarnição} />
                    <LineCardapio title={'Sobremesa'} content={cardapio.Sobremesa} />

                </View>
            </Panel>
        </View>
    );
};

const styles = StyleSheet.create({
    container: {
        padding: 15,
        
    },

    panel: {
        borderRadius: 10,
    },

    title: {
        fontSize: 18,
        alignSelf: 'center',
        fontWeight: 'bold',
        color: '#00c667',
        padding: 10
    },

    divider: {
        backgroundColor: '#00c667'
    },

    containerText: {
        padding: 5
    }, 

    text: {
        fontSize: 20,
        fontWeight: 'bold',
        alignSelf: 'center'
    }
});

export default PanelCardapio;