package esrome.scienceMod.blocks.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * fabricator - Esrome
 * Created using Tabula 7.0.0
 */
public class fabricator extends ModelBase {
    public ModelRenderer Base;
    public ModelRenderer Arm1;
    public ModelRenderer Arm2;
    public ModelRenderer Claw1;
    public ModelRenderer Claw2;
    public ModelRenderer Arm1_1;
    public ModelRenderer Arm2_1;
    public ModelRenderer Head;

    public fabricator() {
        this.textureWidth = 128;
        this.textureHeight = 128;
        this.Arm1_1 = new ModelRenderer(this, 0, 0);
        this.Arm1_1.setRotationPoint(20.0F, -16.0F, 23.3F);
        this.Arm1_1.addBox(0.0F, 0.0F, 0.0F, 2, 8, 2, 0.0F);
        this.setRotateAngle(Arm1_1, 0.0F, 3.141592653589793F, 0.0F);
        this.Claw1 = new ModelRenderer(this, 48, 0);
        this.Claw1.setRotationPoint(0.5F, -11.0F, -0.5F);
        this.Claw1.addBox(0.0F, 0.0F, 0.0F, 2, 4, 2, 0.0F);
        this.Head = new ModelRenderer(this, 48, 0);
        this.Head.setRotationPoint(-1.0F, -12.0F, -1.0F);
        this.Head.addBox(0.0F, 0.0F, 0.0F, 2, 4, 2, 0.0F);
        this.Claw2 = new ModelRenderer(this, 48, 0);
        this.Claw2.setRotationPoint(-3.0F, 0.0F, 0.0F);
        this.Claw2.addBox(0.0F, 0.0F, 0.0F, 2, 4, 2, 0.0F);
        this.Base = new ModelRenderer(this, 0, 0);
        this.Base.setRotationPoint(8.0F, -7.9F, 8.0F);
        this.Base.addBox(0.0F, 0.0F, 0.0F, 16, 1, 16, 0.0F);
        this.Arm1 = new ModelRenderer(this, 0, 0);
        this.Arm1.setRotationPoint(12.0F, -16.0F, 8.0F);
        this.Arm1.addBox(0.0F, 0.0F, 0.0F, 2, 8, 2, 0.0F);
        this.Arm2 = new ModelRenderer(this, 0, 0);
        this.Arm2.setRotationPoint(1.0F, 1.0F, 1.0F);
        this.Arm2.addBox(-1.0F, -8.0F, -1.0F, 2, 8, 2, 0.0F);
        this.setRotateAngle(Arm2, -0.7620752843118808F, 0.0F, 0.0F);
        this.Arm2_1 = new ModelRenderer(this, 0, 0);
        this.Arm2_1.setRotationPoint(1.0F, 1.0F, 1.0F);
        this.Arm2_1.addBox(-1.0F, -8.0F, -1.0F, 2, 8, 2, 0.0F);
        this.setRotateAngle(Arm2_1, -0.7620107514207242F, 0.0F, 0.0F);
        this.Arm2.addChild(this.Claw1);
        this.Arm2_1.addChild(this.Head);
        this.Claw1.addChild(this.Claw2);
        this.Arm1.addChild(this.Arm2);
        this.Arm1_1.addChild(this.Arm2_1);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.Arm1_1.render(f5);
        this.Base.render(f5);
        this.Arm1.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
