package esrome.scienceMod.blocks.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelChest - Either Mojang or a mod author
 * Created using Tabula 7.0.0
 */
public class ModelFabricator extends ModelBase {
    public ModelRenderer Base;
    public ModelRenderer Arm1;
    public ModelRenderer Arm2;
    public ModelRenderer Head;
    public ModelRenderer Arm1_1;
    public ModelRenderer Arm2_1;
    public ModelRenderer Claw1;
    public ModelRenderer Claw2;

    public ModelFabricator() {
        this.textureWidth = 128;
        this.textureHeight = 128;
        this.Arm1 = new ModelRenderer(this, 0, 0);
        this.Arm1.setRotationPoint(1.0F, 7.0F, 6.0F);
        this.Arm1.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, 0.0F);
        this.Base = new ModelRenderer(this, 0, 0);
        this.Base.setRotationPoint(0.0F, 15.0F, 0.0F);
        this.Base.addBox(0.0F, 0.0F, 0.0F, 16, 1, 16, 0.0F);
        this.Arm2 = new ModelRenderer(this, 0, 0);
        this.Arm2.setRotationPoint(0.0F, 1.0F, 0.0F);
        this.Arm2.addBox(-1.0F, -8.0F, -1.0F, 2, 8, 2, 0.0F);
        this.Arm2_1 = new ModelRenderer(this, 0, 0);
        this.Arm2_1.setRotationPoint(0.0F, 1.0F, 0.0F);
        this.Arm2_1.addBox(-1.0F, -8.0F, -1.0F, 2, 8, 2, 0.0F);
        this.Arm1_1 = new ModelRenderer(this, 0, 0);
        this.Arm1_1.setRotationPoint(14.0F, 7.0F, 11.0F);
        this.Arm1_1.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, 0.0F);
        this.Claw1 = new ModelRenderer(this, 48, 0);
        this.Claw1.setRotationPoint(0.5F, -11.0F, -0.5F);
        this.Claw1.addBox(0.0F, 0.0F, 0.0F, 2, 4, 2, 0.0F);
        this.Head = new ModelRenderer(this, 48, 0);
        this.Head.setRotationPoint(-1.0F, -12.0F, -1.0F);
        this.Head.addBox(0.0F, 0.0F, 0.0F, 2, 4, 2, 0.0F);
        this.Claw2 = new ModelRenderer(this, 48, 0);
        this.Claw2.setRotationPoint(-3.0F, 0.0F, 0.0F);
        this.Claw2.addBox(0.0F, 0.0F, 0.0F, 2, 4, 2, 0.0F);
        this.Arm1.addChild(this.Arm2);
        this.Arm1_1.addChild(this.Arm2_1);
        this.Arm2_1.addChild(this.Claw1);
        this.Arm2.addChild(this.Head);
        this.Claw1.addChild(this.Claw2);
    }
    
    public void renderAll(){
        this.Base.render(0.0625F);
        this.Arm1.render(0.0625F);
        this.Arm1_1.render(0.0625F);
    }
    
}
