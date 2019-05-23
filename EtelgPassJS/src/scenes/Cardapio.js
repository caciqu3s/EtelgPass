import React from 'react';
import { ScrollView, View, Text, FlatList, StyleSheet } from 'react-native';
import Panel from 'react-native-panel';

import PanelCardapio from '../components/PanelCardapio';

export default class Cardapio extends React.Component {
    renderCardapio() {
        const { Cardapio } = this.props.screenProps.dados;

        return (
            <FlatList
                data={Cardapio}
                renderItem={({ item }) => (
                    <PanelCardapio cardapio={item} />
                )}
                keyExtractor={(item) => item.Dia} />
        );
    }

    render() {
        return(
            <ScrollView style={styles.container}>
                {this.renderCardapio()}
            </ScrollView>
        );
    }
}

const styles = StyleSheet.create({
    container: {
        paddingTop: 15,
        backgroundColor: '#dcdee2'
    },
});