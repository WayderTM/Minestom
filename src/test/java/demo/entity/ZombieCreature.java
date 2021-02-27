package demo.entity;

import net.minestom.server.entity.ai.goal.RandomLookAroundGoal;
import net.minestom.server.entity.type.monster.EntityZombie;
import net.minestom.server.utils.Position;

public class ZombieCreature extends EntityZombie {

    public ZombieCreature(Position spawnPosition) {
        super(spawnPosition);
        addAIGroup(
                newAIGroupBuilder()
                        .addGoalSelector(new RandomLookAroundGoal(this, 20))
                        .build()
        );
    }
}
