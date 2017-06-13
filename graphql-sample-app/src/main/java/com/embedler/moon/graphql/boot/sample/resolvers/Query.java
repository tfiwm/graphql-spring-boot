package com.embedler.moon.graphql.boot.sample.resolvers;

import com.coxautodev.graphql.tools.GraphQLRootResolver;
import com.embedler.moon.graphql.boot.sample.CharacterRepository;
import com.embedler.moon.graphql.boot.sample.types.Character;
import com.embedler.moon.graphql.boot.sample.types.Droid;
import com.embedler.moon.graphql.boot.sample.types.Episode;
import com.embedler.moon.graphql.boot.sample.types.Human;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Query implements GraphQLRootResolver {

    @Autowired
    private CharacterRepository characterRepository;

    public Character hero(Episode episode) {
        return episode != null ? characterRepository.getHeroes().get(episode)
                : characterRepository.getCharacters().get("1000");
    }

    public Human human(String id) {
        return (Human) characterRepository.getCharacters().values().stream()
                .filter(character -> character instanceof Human && character.getId().equals(id)).findFirst()
                .orElseGet(null);
    }

    public Droid droid(String id) {
        return (Droid) characterRepository.getCharacters().values().stream()
                .filter(character -> character instanceof Droid && character.getId().equals(id)).findFirst()
                .orElseGet(null);
    }

    public Character character(String id) {
        return characterRepository.getCharacters().get(id);
    }
}
